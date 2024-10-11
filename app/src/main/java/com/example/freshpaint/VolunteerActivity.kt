package com.example.freshpaint

import VolunteerAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class VolunteerActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VolunteerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer)

        // Set up the toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.volunteerRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Sample data for testing
        val opportunities = listOf(
            VolunteerOpportunity(R.drawable.ic_volunteer, "Volunteer Opportunity 1", "Date: 5th September 2024", "Help us organize a community event."),
            VolunteerOpportunity(R.drawable.ic_volunteer, "Volunteer Opportunity 2", "Date: 10th September 2024", "Join us for a tree planting event.")
            // Add more items as needed
        )

        // Set up the adapter with a click listener
        adapter = VolunteerAdapter(opportunities) { opportunity ->
            handleSignUp(opportunity.title)
        }
        recyclerView.adapter = adapter
    }

    private fun handleSignUp(opportunity: String) {
        // Handle sign up for a volunteer opportunity
        showSignUpConfirmation(opportunity)
    }

    private fun showSignUpConfirmation(opportunity: String) {
        // Show a simple message confirming the sign-up
        val message = "You've signed up for $opportunity!"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
