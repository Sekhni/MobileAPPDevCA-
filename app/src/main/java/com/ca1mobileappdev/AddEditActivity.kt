package com.ca1mobileappdev

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ca1mobileappdev.models.AppData
import com.ca1mobileappdev.models.RentalBusiness
import timber.log.Timber

class AddEditActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var priceInput: EditText
    private lateinit var ratingInput: EditText

    private var editingId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit)

        nameInput =
            findViewById(R.id.nameInput)

        descriptionInput =
            findViewById(R.id.descriptionInput)

        priceInput =
            findViewById(R.id.priceInput)

        ratingInput =
            findViewById(R.id.ratingInput)

        val saveButton =
            findViewById<Button>(R.id.saveButton)

        val cancelButton =
            findViewById<Button>(R.id.cancelButton)

        editingId =
            intent.getLongExtra("id", -1L)
                .takeIf { it != -1L }

        if (editingId != null) {

            findViewById<TextView>(R.id.formTitle)
                .text = "Edit Business"

            loadExistingBusiness(editingId!!)
        }

        saveButton.setOnClickListener {
            saveBusiness()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun loadExistingBusiness(id: Long) {

        val business =
            AppData.businesses.findOne(id)

        if (business == null) {

            Toast.makeText(
                this,
                "Business not found",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        nameInput.setText(business.name)
        descriptionInput.setText(business.description)
        priceInput.setText(business.pricePerDay.toString())
        ratingInput.setText(business.rating.toString())
    }

    private fun saveBusiness() {

        val name =
            nameInput.text.toString().trim()

        val description =
            descriptionInput.text.toString().trim()

        if (name.isEmpty()) {
            nameInput.error = "Name is required"
            return
        }

        val price =
            priceInput.text.toString().toDoubleOrNull()

        if (price == null) {
            priceInput.error = "Enter a valid number"
            return
        }

        val rating =
            ratingInput.text.toString().toDoubleOrNull()

        if (rating == null) {
            ratingInput.error = "Enter a valid number"
            return
        }

        if (editingId == null) {

            val business = RentalBusiness(
                name = name,
                description = description,
                pricePerDay = price,
                rating = rating
            )

            Timber.d("Creating business name=${business.name}")

            AppData.businesses.create(business)

            Toast.makeText(
                this,
                "Business added",
                Toast.LENGTH_SHORT
            ).show()

        } else {

            val business = RentalBusiness(
                id = editingId!!,
                name = name,
                description = description,
                pricePerDay = price,
                rating = rating
            )

            Timber.d("Updating business id=${business.id}, name=${business.name}")

            AppData.businesses.update(business)

            Toast.makeText(
                this,
                "Business updated",
                Toast.LENGTH_SHORT
            ).show()
        }

        finish()
    }
}