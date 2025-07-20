package com.example.contactsapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapplication.databinding.ActivityHomeBinding
import com.example.contactsapplication.databinding.ContactCardBinding

class RecycleViewerAdapter(var contacts : List<contactDM>) : RecyclerView.Adapter<RecycleViewerAdapter.ViewHolder>() {
    private lateinit var RecyclerViewBinding : ContactCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        RecyclerViewBinding = ContactCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(RecyclerViewBinding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind.userName.text = contact.userName
        holder.bind.email.text = contact.email
        holder.bind.phoneNumber.text = contact.phone
        holder.bind.UserImage.setImageURI(contact.image)
    }

    class ViewHolder(CarditemView: ContactCardBinding) : RecyclerView.ViewHolder(CarditemView.root) {
        val bind = CarditemView
    }
}