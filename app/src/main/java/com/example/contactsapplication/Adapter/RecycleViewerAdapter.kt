package com.example.contactsapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapplication.databinding.ContactCardBinding


class RecycleViewerAdapter(var contacts: List<contactDM>) : RecyclerView.Adapter<RecycleViewerAdapter.ViewHolder>() {
    // Lambda to notify when an item should be removed, passing its position
    var onRemoveContact: ((contact: contactDM) -> Unit)? = null

    // Lambda for when an item is clicked (example, if you need item clicks)
    var onItemClick: ((contact: contactDM) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate a new binding object for EACH ViewHolder instance
        val binding = ContactCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindData(contact)

        // Set click listener for the delete button
        holder.binding.deleteButtonCard.setOnClickListener {
            onRemoveContact?.invoke(contact)
        }

        // Set click listener for the whole item
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(contact)
        }
    }

    // ViewHolder class holds the binding and has a bindData method
    class ViewHolder(val binding: ContactCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(contact: contactDM) {
            binding.userName.text = contact.userName
            binding.email.text = contact.email
            binding.phoneNumber.text = contact.phone
        }
    }
}
