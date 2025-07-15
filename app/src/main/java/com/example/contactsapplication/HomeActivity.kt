package com.example.contactsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.contactsapplication.Adapter.Adapter

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private val maxContacts = 6 ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val view = findViewById<ViewPager2>(R.id.View_Pager)
        view.adapter = Adapter(this)

    }
}