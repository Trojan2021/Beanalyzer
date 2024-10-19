package com.example.breathalyzer_app

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FriendViewActivity : AppCompatActivity() {

    private lateinit var friendAdapter : FriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_friend_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rvListOfFriends = findViewById<RecyclerView>(R.id.rvFriendView)
        val addFriend = findViewById<Button>(R.id.btnAddFriend)

        friendAdapter = FriendAdapter(mutableListOf())
        rvListOfFriends.adapter = friendAdapter
        rvListOfFriends.layoutManager = LinearLayoutManager(this)

        addFriend.setOnClickListener {
            val friendName = "TESTING"
            val bac = "0.08"
            if(friendName.isNotEmpty())
            {
                val friend = Friend(friendName, bac)
                friendAdapter.addFriend(friend)
            }

        }
    }
}