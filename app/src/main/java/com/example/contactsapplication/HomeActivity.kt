package com.example.contactsapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.contactsapplication.Adapter.RecycleViewerAdapter
import com.example.contactsapplication.Adapter.contactDM
import com.example.contactsapplication.Fragments.BottomSheetFragment
import com.example.contactsapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private lateinit var activityHomeBind: ActivityHomeBinding
    private lateinit var recycleViewerAdapter: RecycleViewerAdapter
    private var contacts = mutableListOf<contactDM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBind.root)

        Log.d(TAG, "onCreate: Handle Recycler View")
        // Handle Recycler View in Home Activity (Start with Visibility = False) ----> RecycleViewer
        recycleViewerAdapter = RecycleViewerAdapter(contacts)
        activityHomeBind.recyclerView.adapter = recycleViewerAdapter
        activityHomeBind.recyclerView.isVisible = false

        // Handle Add Button in Home Activity ----> BottomSheetFragment
        activityHomeBind.addButton.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, "bottomSheetFragment")
            bottomSheetFragment.saveContact = { contact ->
                Log.d(TAG, "saveContact: $contact")
                recycleViewerAdapter.addContact(contact)
                activityHomeBind.recyclerView.isVisible = true
                activityHomeBind.EmptyImage.isVisible = false
                activityHomeBind.EmptyText.isVisible = false
            }
        }

        // Handle Remove Button in contactCard ----> RecycleViewer
        recycleViewerAdapter.onRemoveContact = { position, contact ->
            recycleViewerAdapter.removeContact(position, contacts[position])
            Toast.makeText(this, "Item Removed: ${contact.userName} , ${contact.email}", Toast.LENGTH_SHORT)
                .show()
            if (recycleViewerAdapter.itemCount == 0) {
                activityHomeBind.recyclerView.isVisible = false
                activityHomeBind.EmptyImage.isVisible = true
                activityHomeBind.EmptyText.isVisible = true
            }
        }

        // Handle Item Click in contactCard ----> BottomSheetFragment
        recycleViewerAdapter.onItemClick = {
            val contactData = it
            Toast.makeText(this, "Item Clicked: ${contactData.userName} , ${contactData.email}", Toast.LENGTH_SHORT)
                .show()
        }
    }

}


