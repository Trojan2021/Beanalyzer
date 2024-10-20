package com.example.breathalyzer_app

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FriendFinderActivity : AppCompatActivity() {

    private lateinit var friendFinderAdapter : FriendFinderAdapter

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

        imbSearch.setOnClickListener {

        }

    }
}