package com.ca1mobileappdev

import com.ca1mobileappdev.models.RentalBusiness

interface OnBusinessListener {

    fun onBusinessClick(business: RentalBusiness)

    fun onBusinessLongClick(business: RentalBusiness): Boolean
}