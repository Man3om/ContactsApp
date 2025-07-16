package com.example.contactsapplication

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapplication.Adapter.RecycleViewerAdapter
import com.example.contactsapplication.Adapter.contactDM
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private val maxContacts = 6 ;
    private val contacts = ArrayList<contactDM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Handle Recycler View in Home Activity (Start with Visibility = False) ----> RecycleViewer
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = RecycleViewerAdapter(contacts)
        recyclerView.isGone = true


        // Find Views in Home Activity
        val addButton = findViewById<MaterialButton>(R.id.add_button)
        val deleteButton = findViewById<MaterialButton>(R.id.delete_button)
        val emptyImage = findViewById<ImageView>(R.id.Empty_Image)
        val emptyText = findViewById<ImageView>(R.id.Empty_Text)

        // Initialize Views in Home Activity
        deleteButton.isGone = true
        addButton.isGone = false
        emptyText.isGone = false
        emptyImage.isGone = false


        // Handle Adding Contacts in Home Activity
        addButton.setOnClickListener {
            if (contacts.size < maxContacts) {
                // Handle Dialog View in Home Activity
                
                recyclerView.isGone = false
                emptyText.isGone = true
                emptyImage.isGone = true
                deleteButton.isGone = false
                addButton.isGone = true
            }

            if (contacts.size == maxContacts) {
                addButton.isGone = true
                deleteButton.isGone = false
            }
        }
    }
}