package com.example.freshpaint

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat

class AdminHomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var userManagementCard: CardView
    private lateinit var contentManagementCard: CardView
    private lateinit var settingsCard: CardView
    private lateinit var reportsCard: CardView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        // Set up the toolbar
        val toolbar = findViewById<Toolbar>(R.id.adminToolbar)
        setSupportActionBar(toolbar)

        // Initialize UI components
        userManagementCard = findViewById(R.id.userManagementCard)
        contentManagementCard = findViewById(R.id.contentManagementCard)
        settingsCard = findViewById(R.id.settingsCard)
        reportsCard = findViewById(R.id.reportsCard)
        drawerLayout = findViewById(R.id.adminDrawerLayout)

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
        val navView = findViewById<NavigationView>(R.id.adminNavigationView)
        navView.setNavigationItemSelectedListener(this)

        // Set click listeners for Cards
        userManagementCard.setOnClickListener {
            val intent = Intent(this, UserManagementActivity::class.java)
            startActivity(intent)
        }

        contentManagementCard.setOnClickListener {
            val intent = Intent(this, ContentManagementActivity::class.java)
            startActivity(intent)
        }

        settingsCard.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        reportsCard.setOnClickListener {
            val intent = Intent(this, ReportsActivity::class.java)
            startActivity(intent)
        }
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
            R.id.nav_user_management -> {
                val intent = Intent(this, UserManagementActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_content_management -> {
                val intent = Intent(this, ContentManagementActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_reports -> {
                val intent = Intent(this, ReportsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

