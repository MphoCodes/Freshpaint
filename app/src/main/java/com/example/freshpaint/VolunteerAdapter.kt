import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freshpaint.R
import com.example.freshpaint.VolunteerOpportunity

class VolunteerAdapter(
    private val opportunities: List<VolunteerOpportunity>,
    private val onSignUpClick: (VolunteerOpportunity) -> Unit
) : RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_volunteer, parent, false)
        return VolunteerViewHolder(view)
    }

    override fun onBindViewHolder(holder: VolunteerViewHolder, position: Int) {
        val opportunity = opportunities[position]
        holder.bind(opportunity, onSignUpClick)
    }

    override fun getItemCount() = opportunities.size

    class VolunteerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.volunteerImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.volunteerTitleTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.volunteerDateTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.volunteerDescriptionTextView)
        private val signUpButton: Button = itemView.findViewById(R.id.signUpButton)

        fun bind(opportunity: VolunteerOpportunity, onSignUpClick: (VolunteerOpportunity) -> Unit) {
            imageView.setImageResource(opportunity.imageResId)
            titleTextView.text = opportunity.title
            dateTextView.text = opportunity.date
            descriptionTextView.text = opportunity.description
            signUpButton.setOnClickListener { onSignUpClick(opportunity) }
        }
    }
}
