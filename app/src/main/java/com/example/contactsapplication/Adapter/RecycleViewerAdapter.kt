package com.example.contactsapplication.Adapter

import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapplication.databinding.ActivityHomeBinding
import com.example.contactsapplication.databinding.ContactCardBinding

class RecycleViewerAdapter(var contacts : List<contactDM>) : RecyclerView.Adapter<RecycleViewerAdapter.ViewHolder>() {
    private lateinit var RecyclerViewBinding : ContactCardBinding
    private lateinit var pickImage: ActivityResultLauncher<String>
    var removecontact: ((Int) -> Unit)? = null ;

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

        holder.bind.deleteButtonCard.setOnClickListener {
            removecontact
        }
    }

    class ViewHolder(CarditemView: ContactCardBinding) : RecyclerView.ViewHolder(CarditemView.root) {
        val bind = CarditemView
    }
}