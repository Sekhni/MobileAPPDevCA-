package com.ca1mobileappdev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewBusinessesButton = findViewById<Button>(R.id.viewBusinessesButton)
        val addBusinessButton = findViewById<Button>(R.id.addBusinessButton)

        viewBusinessesButton.setOnClickListener {
            startActivity(Intent(this, BusinessListActivity::class.java))
        }

        addBusinessButton.setOnClickListener {
            startActivity(Intent(this, AddEditActivity::class.java))
        }
    }
}