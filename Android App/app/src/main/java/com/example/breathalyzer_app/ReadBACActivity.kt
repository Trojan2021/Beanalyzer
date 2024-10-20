package com.example.breathalyzer_app

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class ReadBACActivity : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue
    private var lastValue : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_read_bacactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSubmit = findViewById<Button>(R.id.btnSubmitBAC)
        val btnBack = findViewById<Button>(R.id.btnBackBAC)
        val upperText = findViewById<TextView>(R.id.tvTopString)
        val lowerText = findViewById<TextView>(R.id.tvLowerString)

        upperText.text = "Welcome"
        lowerText.text = "Click Start to Record"
        btnSubmit.text = "Start"

        btnSubmit.setOnClickListener {
            upperText.text = "Keep Blowing"
            lowerText.text = "30"
            btnSubmit.text = "Stop"

            val timer = object : CountDownTimer(30000L, 1000L) {
                var isRunning : Boolean = true
                // This method is called on every tick (every second)
                override fun onTick(millisUntilFinished: Long) {
                    // Update lowerText with the remaining time in seconds
                    if(isRunning) {
                        lowerText.text = (millisUntilFinished / 1000).toString()
                    }

                }

                // This method is called when the countdown finishes
                override fun onFinish() {
                    // Change the upperText to "Done" when the countdown finishes
                    upperText.text = "Done"
                    requestQueue = Volley.newRequestQueue(this@ReadBACActivity)
                    if(lastValue != null)
                        lowerText.text = "${lastValue}%"
                    else
                        lowerText.text = "-0.1%"
                    btnSubmit.text = "Start"
                }
            }

            fun cancelTimer() {
                timer.isRunning = false
                timer.cancel()
                upperText.text = "Reading Canceled"
                lowerText.text = "Click Start to Record"
                btnSubmit.text = "Start"

            }
            if(timer.isRunning) {
                timer.start()
            }
            else{
                cancelTimer()
            }
        }
        btnBack.setOnClickListener {
            finish()
        }
    }
    private fun makeApiCall() {
        val url = "https://io.adafruit.com/api/v2/g_nerone/feeds?x-aio-key= API_KEY HERE" // Replace with your API endpoint

        val stringRequest = object : StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                // Handle the response
                println("Response: $response")
                parseFeeds(response)
            },
            Response.ErrorListener { error: VolleyError ->
                // Handle error
                println("Error: ${error.message}")
            }) {
        }

        // Add the request to the RequestQueue
        requestQueue.add(stringRequest)
    }
    private fun parseFeeds(response: String){
        try {
            val jsonArray = JSONArray(response)
            val targetId = "2911675" // Specify the ID you're looking for
            lastValue = null

            for (i in 0 until jsonArray.length()) {
                val feed = jsonArray.getJSONObject(i)
                val feedId = feed.getString("id") // Get the feed ID

                // Check if this is the target ID
                if (feedId == targetId) {
                    lastValue = feed.getString("last_value") // Get the last_value
                    break // Exit the loop since we found the target feed
                }
            }

            // Handle the last_value as needed
            if (lastValue != null) {
                println("Last Value for ID $targetId: $lastValue")
            } else {
                println("Feed with ID $targetId not found.")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}