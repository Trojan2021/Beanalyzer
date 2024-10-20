package com.example.breathalyzer_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendAdapter (
    private val friends: MutableList<Friend>
):RecyclerView.Adapter<FriendAdapter.FriendViewHolder>(){

    class FriendViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvFriendTrackComp: TextView = itemView.findViewById(R.id.tvFriendTrackComp)
        val tvPlayerBac : TextView = itemView.findViewById(R.id.tvPlayerBAC)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_friend_view, parent, false)
        )
    }
    fun addFriend(friend: Friend)
    {
        friends.add(friend)
        notifyItemInserted(friends.size - 1)
    }
    fun deleteFriend(friend: Friend)
    {
        friends.remove(friend)
        notifyDataSetChanged()
    }
    fun get

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val curFriend = friends[position]
        holder.apply{
            tvFriendTrackComp.text = curFriend.friend
            tvPlayerBac.text = curFriend.bac
        }
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    //class FriendViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}