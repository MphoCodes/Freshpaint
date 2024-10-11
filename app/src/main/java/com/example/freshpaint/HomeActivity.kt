package com.example.freshpaint


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.cardview.widget.CardView
import androidx.viewpager2.widget.ViewPager2

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var welcomeTextView: TextView
    private lateinit var profileCard: CardView
    private lateinit var donateCard: CardView
    private lateinit var volunteerCard: CardView
    private lateinit var eventsCard: CardView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var loadingAnimationView: LottieAnimationView
    private lateinit var carouselViewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Set up the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize UI components
        welcomeTextView = findViewById(R.id.welcomeTextView)
        profileCard = findViewById(R.id.profileCard)
        donateCard = findViewById(R.id.donateCard)
        volunteerCard = findViewById(R.id.volunteerCard)
        eventsCard = findViewById(R.id.eventsCard)
        drawerLayout = findViewById(R.id.drawer_layout)
        loadingAnimationView = findViewById(R.id.loadingAnimationView)
        carouselViewPager = findViewById(R.id.carouselViewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // Set the welcome message
        val username = intent.getStringExtra("USERNAME") ?: "User"
        welcomeTextView.text = "Welcome, $username!"

        // Set up drawer toggle
        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        // Set up the NavigationView
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        // Set up the carousel
        val carouselItems = listOf(
            Event("Event Title 1", "25th August 2024", "Brief description of the event goes here.", R.drawable.ic_events),
            Event("Event Title 2", "30th August 2024", "Brief description of the event goes here.", R.drawable.ic_events)
        )
        carouselViewPager.adapter = EventCarouselAdapter(carouselItems)

        // Set up TabLayout with ViewPager2
        TabLayoutMediator(tabLayout, carouselViewPager) { tab, position ->
            tab.text = "Event ${position + 1}" // Customize tab titles if needed
        }.attach()

        // Set click listeners for Cards
        profileCard.setOnClickListener {
            showLoadingAnimation {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }

        donateCard.setOnClickListener {
            showLoadingAnimation {
                val intent = Intent(this, DonateActivity::class.java)
                startActivity(intent)
            }
        }

        volunteerCard.setOnClickListener {
            showLoadingAnimation {
                val intent = Intent(this, VolunteerActivity::class.java)
                startActivity(intent)
            }
        }

        eventsCard.setOnClickListener {
            showLoadingAnimation {
                val intent = Intent(this, EventsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showLoadingAnimation(onAnimationComplete: () -> Unit) {
        loadingAnimationView.visibility = View.VISIBLE
        loadingAnimationView.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            loadingAnimationView.cancelAnimation()
            loadingAnimationView.visibility = View.GONE
            onAnimationComplete()
        }, 1500) // 1.5 seconds delay
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {
                showLoadingAnimation {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.action_donate -> {
                showLoadingAnimation {
                    val intent = Intent(this, DonateActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.action_volunteer -> {
                showLoadingAnimation {
                    val intent = Intent(this, VolunteerActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.action_events -> {
                showLoadingAnimation {
                    val intent = Intent(this, EventsActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.action_sign_out -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
