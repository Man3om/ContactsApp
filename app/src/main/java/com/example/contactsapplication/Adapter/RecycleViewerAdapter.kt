package com.example.contactsapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapplication.R

class RecycleViewerAdapter(private val contacts : ArrayList<contactDM> , private val onClickListener: ContcatonClickListener? = null) : RecyclerView.Adapter<RecycleViewerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(contacts[position].image)
        holder.name.text = contacts[position].userName
        holder.email.text = contacts[position].email
        holder.phone.text = contacts[position].phone

        holder.itemView.setOnClickListener {
            onClickListener?.onClick()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.User_Image)
        val name = itemView.findViewById<TextView>(R.id.user_name)
        val email = itemView.findViewById<TextView>(R.id.email)
        val phone = itemView.findViewById<TextView>(R.id.phone_number)
    }
}