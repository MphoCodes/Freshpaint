// EventVolunteerAdapter.kt
package com.example.freshpaint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventVolunteerAdapter(private val volunteers: List<String>) :
    RecyclerView.Adapter<EventVolunteerAdapter.VolunteerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_volunteer, parent, false)
        return VolunteerViewHolder(view)
    }

    override fun onBindViewHolder(holder: VolunteerViewHolder, position: Int) {
        val volunteer = volunteers[position]
        holder.nameTextView.text = volunteer
    }

    override fun getItemCount(): Int = volunteers.size

    class VolunteerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.volunteerNameTextView)
    }
}
