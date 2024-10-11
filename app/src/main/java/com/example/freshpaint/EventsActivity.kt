package com.example.freshpaint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class EventsActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var eventsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        // Set up the toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Initialize RecyclerView
        eventsRecyclerView = findViewById(R.id.eventsRecyclerView)
        eventsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Create sample event data
        val events = listOf(
            Event("Event Title 1", "25th August 2024", "Brief description of the event goes here.", R.drawable.ic_events),
            Event("Event Title 2", "30th August 2024", "Brief description of the event goes here.", R.drawable.ic_events)
        )

        // Set up the adapter
        val adapter = EventsAdapter(events)
        eventsRecyclerView.adapter = adapter
    }
}


class EventsAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.eventImageView)
        val titleTextView: TextView = view.findViewById(R.id.eventTitleTextView)
        val dateTextView: TextView = view.findViewById(R.id.eventDateTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.eventDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_events, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.imageView.setImageResource(event.imageResId)
        holder.titleTextView.text = event.title
        holder.dateTextView.text = event.date
        holder.descriptionTextView.text = event.description
    }

    override fun getItemCount() = events.size
}