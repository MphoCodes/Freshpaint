package com.example.freshpaint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ActivityVolunteerManagement : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var volunteerAdapter: EventVolunteerAdapter
    private val db = FirebaseFirestore.getInstance()
    private val volunteerList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_management)

        recyclerView = findViewById(R.id.volunteerRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        volunteerAdapter = EventVolunteerAdapter(volunteerList)
        recyclerView.adapter = volunteerAdapter

        // Load volunteers
        loadVolunteers()
    }

    private fun loadVolunteers() {
        // Get the event ID from intent or other source
        val eventId = intent.getStringExtra("EVENT_ID") ?: return

        db.collection("Events").document(eventId).collection("Volunteers")
            .get()
            .addOnSuccessListener { result ->
                volunteerList.clear()
                for (document in result) {
                    val volunteerName = document.getString("name") ?: "Unknown"
                    volunteerList.add(volunteerName)
                }
                volunteerAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // Handle error
            }
    }
}
