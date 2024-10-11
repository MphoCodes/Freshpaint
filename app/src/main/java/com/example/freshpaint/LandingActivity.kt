package com.example.freshpaint

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class LandingActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        // Initialize UI components
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        videoView = findViewById(R.id.backgroundVideoView)

        // Set up video background
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.background_video)
        videoView.setVideoURI(videoUri)

        // Set up video scaling to fill the screen
        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true  // Loop the video
            // Adjust video scaling to fill screen
            val videoWidth = mediaPlayer.videoWidth
            val videoHeight = mediaPlayer.videoHeight
            val screenWidth = resources.displayMetrics.widthPixels
            val screenHeight = resources.displayMetrics.heightPixels

            val xScale = screenWidth.toFloat() / videoWidth
            val yScale = screenHeight.toFloat() / videoHeight
            val scale = xScale.coerceAtLeast(yScale)

            val scaledWidth = (scale * videoWidth).toInt()
            val scaledHeight = (scale * videoHeight).toInt()

            videoView.layoutParams.width = scaledWidth
            videoView.layoutParams.height = scaledHeight
            videoView.requestLayout()
        }

        videoView.start()

        // Set click listeners
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
