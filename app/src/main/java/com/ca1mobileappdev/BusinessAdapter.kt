package com.ca1mobileappdev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ca1mobileappdev.models.RentalBusiness

class BusinessAdapter(
    private var businesses: List<RentalBusiness>,
    private val onEdit: (RentalBusiness) -> Unit,
    private val onDelete: (RentalBusiness) -> Unit
) : RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>() {

    class BusinessViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val nameText: TextView =
            itemView.findViewById(R.id.nameText)

        val descriptionText: TextView =
            itemView.findViewById(R.id.descriptionText)

        val detailsText: TextView =
            itemView.findViewById(R.id.detailsText)

        val editButton: Button =
            itemView.findViewById(R.id.editButton)

        val deleteButton: Button =
            itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BusinessViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_business,
                parent,
                false
            )

        return BusinessViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BusinessViewHolder,
        position: Int
    ) {

        val business = businesses[position]

        holder.nameText.text =
            "${business.id}: ${business.name}"

        holder.descriptionText.text =
            business.description

        holder.detailsText.text =
            "Price: ${business.pricePerDay}, Rating: ${business.rating}"

        holder.editButton.setOnClickListener {
            onEdit(business)
        }

        holder.deleteButton.setOnClickListener {
            onDelete(business)
        }
    }

    override fun getItemCount(): Int {
        return businesses.size
    }

    fun updateBusinesses(newBusinesses: List<RentalBusiness>) {
        businesses = newBusinesses
        notifyDataSetChanged()
    }
}