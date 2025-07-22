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
        recycleViewerAdapter = RecycleViewerAdapter(listOf())
        activityHomeBind.recyclerView.adapter = recycleViewerAdapter
        activityHomeBind.recyclerView.isVisible = false

        // Handle Add Button in Home Activity ----> BottomSheetFragment
        activityHomeBind.addButton.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, "bottomSheetFragment")
            bottomSheetFragment.saveContact = {
                Log.d(TAG, "saveContact: $it")
                addContact(it)
                activityHomeBind.recyclerView.isVisible = true
                activityHomeBind.EmptyImage.isVisible = false
                activityHomeBind.EmptyText.isVisible = false
            }
        }

        // Handle Remove Button in contactCard ----> RecycleViewer
        recycleViewerAdapter.onRemoveContact = {
            removeContact(contacts.indexOf(it))
        }

        // Handle Item Click in contactCard ----> BottomSheetFragment
        recycleViewerAdapter.onItemClick = {
            val contcatData = it
            Toast.makeText(this, "Item Clicked: ${contcatData.userName} , ${contcatData.email}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun addContact(contact: contactDM) {
        contacts.add(contact)
        recycleViewerAdapter.contacts = contacts
        recycleViewerAdapter.notifyItemInserted(contacts.size - 1)
        Log.d(TAG, "addContact: $contacts")
    }

    private fun removeContact(position: Int) {
        contacts.removeAt(position)
        if (contacts.isEmpty()) {
            activityHomeBind.recyclerView.isVisible = false
            activityHomeBind.EmptyImage.isVisible = true
            activityHomeBind.EmptyText.isVisible = true
        }
        recycleViewerAdapter.contacts = contacts
        recycleViewerAdapter.notifyItemRemoved(position)
        Log.d(TAG, "removeContact: $contacts")
    }
}


