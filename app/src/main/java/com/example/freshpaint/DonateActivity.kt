package com.example.freshpaint

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.Toast

class DonateActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var donationAmountEditText: EditText
    private lateinit var paymentMethodRadioGroup: RadioGroup
    private lateinit var submitDonationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        // Set up the toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Initialize UI components
        donationAmountEditText = findViewById(R.id.donationAmountEditText)
        paymentMethodRadioGroup = findViewById(R.id.paymentMethodRadioGroup)
        submitDonationButton = findViewById(R.id.submitDonationButton)

        // Set up the submit button click listener
        submitDonationButton.setOnClickListener {
            handleDonation()
        }
    }

    private fun handleDonation() {
        val amount = donationAmountEditText.text.toString().trim()
        if (amount.isEmpty()) {
            Toast.makeText(this, "Please enter a donation amount", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedPaymentMethodId = paymentMethodRadioGroup.checkedRadioButtonId
        if (selectedPaymentMethodId == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedPaymentMethod = findViewById<RadioButton>(selectedPaymentMethodId).text
        Toast.makeText(this, "Donation of $amount using $selectedPaymentMethod is processed", Toast.LENGTH_LONG).show()

        // Here you would usually handle the donation logic, such as sending the data to a server
    }
}
