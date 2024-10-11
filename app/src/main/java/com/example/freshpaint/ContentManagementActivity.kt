package com.example.freshpaint

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

class ContentManagementActivity : AppCompatActivity() {

    // Declare TextInputLayouts and EditTexts
    private lateinit var titleInputLayout: TextInputLayout
    private lateinit var descriptionInputLayout: TextInputLayout
    private lateinit var dateRangeInputLayout: TextInputLayout
    private lateinit var locationInputLayout: TextInputLayout
    private lateinit var titleInput: TextInputEditText
    private lateinit var descriptionInput: TextInputEditText
    private lateinit var dateRangeInput: TextInputEditText
    private lateinit var locationInput: TextInputEditText
    private lateinit var uploadedImageView: ImageView
    private lateinit var uploadImageButton: Button
    private lateinit var saveButton: Button
    private lateinit var noImageText: TextView

    private val database = FirebaseDatabase.getInstance()
    private val eventsRef = database.getReference("Events")
    private val storage = FirebaseStorage.getInstance()
    private lateinit var storageReference: StorageReference

    private var imageUri: Uri? = null
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_management)

        // Initialize views
        titleInputLayout = findViewById(R.id.titleInputLayout)
        titleInput = findViewById(R.id.titleInput)
        descriptionInputLayout = findViewById(R.id.descriptionInputLayout)
        descriptionInput = findViewById(R.id.descriptionInput)
        dateRangeInputLayout = findViewById(R.id.dateRangeInputLayout)
        dateRangeInput = findViewById(R.id.dateRangeInput)
        locationInputLayout = findViewById(R.id.locationInputLayout)
        locationInput = findViewById(R.id.locationInput)
        uploadedImageView = findViewById(R.id.uploadedImageView)
        uploadImageButton = findViewById(R.id.uploadImageButton)
        saveButton = findViewById(R.id.saveButton)
        noImageText = findViewById(R.id.noImageText)

        storageReference = storage.reference

        // Image upload button click listener
        uploadImageButton.setOnClickListener {
            openImagePicker()
        }

        // Date range input click listener
        dateRangeInput.setOnClickListener {
            openDateRangePicker()
        }

        // Save button click listener
        saveButton.setOnClickListener {
            saveEvent()
        }
    }

    // Open image picker when upload button is clicked
    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    // Handle the result of the image picker
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE && data != null) {
            imageUri = data.data
            uploadedImageView.setImageURI(imageUri) // Display selected image
            uploadImageToFirebase()

            // Hide "No image uploaded" text
            noImageText.visibility = View.GONE
        }
    }

    // Upload the image to Firebase Storage
    private fun uploadImageToFirebase() {
        if (imageUri != null) {
            val fileRef = storageReference.child("images/${UUID.randomUUID()}.jpg")
            val uploadTask = fileRef.putFile(imageUri!!)

            uploadTask.addOnSuccessListener {
                // Get the download URL after the image is uploaded
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    imageUrl = uri.toString()
                    Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Open date range picker for event start and end dates
    private fun openDateRangePicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Select Event Date Range")
            .build()

        datePicker.addOnPositiveButtonClickListener { dateRange ->
            // Get the start and end dates
            val startDate = dateRange.first
            val endDate = dateRange.second
            // Format and display the date range
            dateRangeInput.setText("${formatDate(startDate)} - ${formatDate(endDate)}")
        }

        datePicker.show(supportFragmentManager, "DATE_PICKER")
    }

    // Format date to display in TextInputEditText
    private fun formatDate(timestamp: Long?): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp ?: 0L))
    }

    // Save event data to Realtime Database
    private fun saveEvent() {
        val title = titleInput.text.toString().trim()
        val description = descriptionInput.text.toString().trim()
        val dateRange = dateRangeInput.text.toString().trim()
        val location = locationInput.text.toString().trim()

        // Validate input fields
        if (title.isEmpty()) {
            titleInputLayout.error = "Title is required"
            return
        } else {
            titleInputLayout.error = null
        }

        if (description.isEmpty()) {
            descriptionInputLayout.error = "Description is required"
            return
        } else {
            descriptionInputLayout.error = null
        }

        if (dateRange.isEmpty()) {
            dateRangeInputLayout.error = "Date range is required"
            return
        } else {
            dateRangeInputLayout.error = null
        }

        if (location.isEmpty()) {
            locationInputLayout.error = "Location is required"
            return
        } else {
            locationInputLayout.error = null
        }

        if (imageUrl == null) {
            Toast.makeText(this, "Please upload an image", Toast.LENGTH_SHORT).show()
            return
        }

        // Prepare event data
        val eventId = eventsRef.push().key // Generate a new unique ID
        val event = hashMapOf(
            "Title" to title,
            "Description" to description,
            "DateRange" to dateRange,
            "Location" to location,
            "ImageUrl" to imageUrl
        )

        // Save event to Realtime Database
        eventId?.let {
            eventsRef.child(it).setValue(event)
                .addOnSuccessListener {
                    Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close activity after saving
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to add event", Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}
