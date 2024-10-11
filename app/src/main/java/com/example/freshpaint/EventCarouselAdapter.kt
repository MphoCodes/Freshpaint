package com.example.freshpaint
import com.bumptech.glide.Glide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventCarouselAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<EventCarouselAdapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventImageView: ImageView = view.findViewById(R.id.eventImageView)
        val eventTitleTextView: TextView = view.findViewById(R.id.eventTitleTextView)
        val eventDescriptionTextView: TextView = view.findViewById(R.id.eventDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carousel_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.eventTitleTextView.text = event.title
        holder.eventDescriptionTextView.text = event.description
        Glide.with(holder.itemView.context)
            .load(event.imageResId)
            .into(holder.eventImageView)
    }

    override fun getItemCount(): Int {
        return events.size
    }
}
