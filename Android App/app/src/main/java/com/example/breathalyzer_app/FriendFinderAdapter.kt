package com.example.breathalyzer_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore

class FriendFinderAdapter(
    private val friends: MutableList<FriendFinder>
) : RecyclerView.Adapter<FriendFinderAdapter.FriendFinderViewHolder>(){

    class FriendFinderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val btdAdd: TextView = itemView.findViewById<Button>(R.id.btnAdd)
        val tvPlayerBac : TextView = itemView.findViewById<TextView>(R.id.tvFriendName)
    }
    fun addFriend(name : FriendFinder)
    {
        friends.add(name)
    }
    fun deleteFriend(name : FriendFinder)
    {
        friends.remove(name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendFinderAdapter.FriendFinderViewHolder {
        return FriendFinderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.add_new_friend, parent, false)
        )
    }
    override fun onBindViewHolder(holder: FriendFinderViewHolder, position: Int) {
        val curFriend = friends[position]
        holder.apply{
            tvPlayerBac.text = curFriend.friend
            curFriend.addBtn.setOnClickListener {
                addToDatabase(curFriend)
                deleteFriend(curFriend)
            }
        }
    }
    override fun getItemCount(): Int {
        return friends.size
    }

   private fun addToDatabase(friend : FriendFinder) {
        val user = FirebaseAuth.getInstance().currentUser
        val dbUser = user?.email ?: "No email"

        val db = Firebase.firestore

        var data: MutableMap<String, Any>? = null
        val docRef = db.collection("friends").document(dbUser)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    data = document.data
                } else {
                    Log.d("Friend Finder", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Friend Finder", "get failed with ", exception)
            }
        var users = mutableListOf<String>()
        if(data != null) {
            users = data?.get("addUsers") as MutableList<String>
        }
        users.add(friend.email)
        db.collection("Users").document(dbUser).set(users)
    }
}