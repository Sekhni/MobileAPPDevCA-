package com.ca1mobileappdev.models

data class RentalBusiness(
    var id: Long = 0L,
    var name: String = "",
    var description: String = "",
    var pricePerDay: Double = 0.0,
    var rating: Double = 0.0
)