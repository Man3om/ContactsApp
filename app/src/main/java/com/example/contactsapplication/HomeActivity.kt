package com.example.contactsapplication

import android.os.Bundle
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
    private val contacts = mutableListOf<contactDM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBind.root)

        // Handle Recycler View in Home Activity (Start with Visibility = False) ----> RecycleViewer
        recycleViewerAdapter = RecycleViewerAdapter(listOf())
        activityHomeBind.recyclerView.adapter = recycleViewerAdapter
        activityHomeBind.recyclerView.isVisible = false


        activityHomeBind.addButton.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            bottomSheetFragment.saveContact = {
                recycleViewerAdapter.contacts += it
                recycleViewerAdapter.notifyItemInserted(contacts.size - 1)
            }

        }

    }
}


