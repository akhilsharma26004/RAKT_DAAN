package com.example.rakt_daan

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rakt_daan.DonorInformation.DonorInformation

class DonorRecycleView(
    private val donorInformation: ArrayList<DonorInformation>,
    private val callBack: RequestBloodScreen
) : RecyclerView.Adapter<DonorRecycleView.InnerDonorRecycleView>() {

    // Define a proper interface for callbacks
    interface CallBack {
        fun onItemClicked(donorInformation: DonorInformation)
    }

    // Inflate the item layout and create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerDonorRecycleView {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.request_layout, parent, false)
        return InnerDonorRecycleView(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: InnerDonorRecycleView, position: Int) {
        val donor = donorInformation[position]
        holder.txt_name.text = donor.name
        holder.txt_address.text = donor.city
        holder.btn_B.text = donor.blood_type

        // Set donor image if available
        if (donor.image != "no image") {
            holder.img_donor.setImageURI(Uri.parse(donor.image))
        } else {
            holder.img_donor.setImageResource(R.drawable.placeholder__1_) // Add a placeholder image if needed
        }

        // Set up the click listener
        holder.ask_for_help_btn.setOnClickListener {
            callBack.onItemClicked(donor)
        }
    }

    // Return the size of the dataset
    override fun getItemCount(): Int {
        return donorInformation.size
    }

    // ViewHolder class to hold the views
    class InnerDonorRecycleView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_donor: ImageView = itemView.findViewById(R.id.img_donor)
        val txt_name: TextView = itemView.findViewById(R.id.txt_name)
        val txt_address: TextView = itemView.findViewById(R.id.txt_address)
        val btn_B: Button = itemView.findViewById(R.id.btn_B)
        val ask_for_help_btn: Button = itemView.findViewById(R.id.ask_for_help_btn)
    }
}
