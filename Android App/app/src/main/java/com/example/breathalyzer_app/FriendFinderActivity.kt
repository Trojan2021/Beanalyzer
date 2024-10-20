package com.example.breathalyzer_app

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore

class FriendFinderActivity : AppCompatActivity() {

    private lateinit var friendFinderAdapter: FriendFinderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_friend_finder)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rvSearchedFriends = findViewById<RecyclerView>(R.id.rvSearchedFriends)
        val imbSearch = findViewById<ImageButton>(R.id.imSearchButton)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        friendFinderAdapter = FriendFinderAdapter(mutableListOf())
        rvSearchedFriends.adapter = friendFinderAdapter
        rvSearchedFriends.layoutManager = LinearLayoutManager(this)
        /*
        imbSearch.setOnClickListener {
            val db = Firebase.firestore
            val search = etSearch.text.toString()
            val mapEntries = getListFromDB()
            val listOfUsers : List<String> = mapEntries?.get("addUsers")
            val userList : MutableList<String> = mutableListOf()
            for(user in listOfUsers)
            {
                val friendFinder = FriendFinder(
                    friend = user
                    email = user.
                )
                userList.add(user)
            }
        }

    }
    fun getListFromDB() : MutableMap<String, Any>?
    {
        val user = FirebaseAuth.getInstance().currentUser
        val dbUser = user?.email ?: "No email"

        val db = Firebase.firestore

        var data: MutableMap<String, Any>? = null
        val docRef = db.collection("Users").document(dbUser)
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
        return data
    }
    */
    }
}