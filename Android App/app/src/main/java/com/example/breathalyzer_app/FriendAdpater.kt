package com.example.breathalyzer_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FriendAdpater (
    private val friends: MutableList<Friend>
):RecyclerView.Adapter<FriendAdpater.FriendViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.singe_friend_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return friends.size
    }

    class FriendViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}