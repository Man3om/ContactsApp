package com.example.contactsapplication.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.contactsapplication.Fragments.*

class Adapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3 ;
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> emptyScreen()
            1 -> addingScreen()
            2 -> fullScreen()
            else -> fullScreen()
        }
    }
}