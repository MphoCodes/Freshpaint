package com.example.freshpaint

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class AdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin) // Use your layout file here

        drawerLayout = findViewById(R.id.adminDrawerLayout)
        val navView: NavigationView = findViewById(R.id.adminNavigationView)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, findViewById(R.id.adminToolbar),
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_user_management -> {
                // Handle User Management click
            }
            R.id.nav_content_management -> {
                // Navigate to Content Management Activity
                val intent = Intent(this, ContentManagementActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_settings -> {
                // Handle Settings click
            }
            R.id.nav_reports -> {
                // Handle Reports click
            }
            R.id.nav_logout -> {
                // Handle Logout click
            }
        }
        drawerLayout.closeDrawers()
        return true
    }
}
