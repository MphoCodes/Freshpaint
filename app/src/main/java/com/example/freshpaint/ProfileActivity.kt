package com.example.freshpaint

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ProfileActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var profileImageView: ImageView
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var editProfileButton: Button
    private lateinit var signOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Set up the toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Initialize views
        profileImageView = findViewById(R.id.profileImageView)
        usernameTextView = findViewById(R.id.usernameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        editProfileButton = findViewById(R.id.editProfileButton)
        signOutButton = findViewById(R.id.signOutButton)

        // Set up user data (example data for demonstration)
        // In a real app, this data might come from a user profile model or a database
        usernameTextView.text = "Username: John Doe"
        emailTextView.text = "Email: johndoe@example.com"

        // Set up click listeners for buttons
        editProfileButton.setOnClickListener {
            // Handle the edit profile action
            // For demonstration, we'll just show a Toast message
            showEditProfileScreen()
        }

        signOutButton.setOnClickListener {
            // Handle the sign-out action
            // For demonstration, we'll just show a Toast message
            performSignOut()
        }
    }

    private fun showEditProfileScreen() {
        // Show a simple message or start an activity to edit the profile
        // In a real application, you might start an EditProfileActivity
        Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
    }

    private fun performSignOut() {
        // Show a simple message or handle sign-out logic
        // In a real application, you might clear user session data and return to a login screen
        Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show()
    }
}
