package com.ca1mobileappdev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ca1mobileappdev.models.AppData
import com.ca1mobileappdev.models.RentalBusiness

class BusinessListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BusinessAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_business_list)

        recyclerView =
            findViewById(R.id.businessesRecyclerView)

        adapter = BusinessAdapter(
            businesses = AppData.businesses.findAll(),

            onEdit = { business ->
                editBusiness(business)
            },

            onDelete = { business ->
                deleteBusiness(business)
            }
        )

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        val returnButton =
            findViewById<Button>(R.id.returnButton)

        returnButton.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        if (::adapter.isInitialized) {

            adapter.updateBusinesses(
                AppData.businesses.findAll()
            )
        }
    }

    private fun editBusiness(business: RentalBusiness) {

        val intent =
            Intent(this, AddEditActivity::class.java)

        intent.putExtra("id", business.id)

        startActivity(intent)
    }

    private fun deleteBusiness(business: RentalBusiness) {

        AppData.businesses.delete(business.id)

        adapter.updateBusinesses(
            AppData.businesses.findAll()
        )
    }
}