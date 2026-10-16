package com.ca1mobileappdev.models

import java.util.concurrent.atomic.AtomicLong

class RentalBusinessList {
    private val businesses = ArrayList<RentalBusiness>()
    private val lastId = AtomicLong(0L)

    fun findAll(): List<RentalBusiness> {
        return businesses
    }

    fun create(business: RentalBusiness) {
        business.id = lastId.incrementAndGet()
        businesses.add(business)
    }

    fun update(business: RentalBusiness): Boolean {
        val foundBusiness = findOne(business.id)
        return if (foundBusiness != null) {
            foundBusiness.name = business.name
            foundBusiness.description = business.description
            foundBusiness.pricePerDay = business.pricePerDay
            foundBusiness.rating = business.rating
            true
        } else {
            false
        }
    }

    fun delete(id: Long): Boolean {
        val foundBusiness = findOne(id)
        return if (foundBusiness != null) {
            businesses.remove(foundBusiness)
            true
        } else {
            false
        }
    }

    fun findOne(id: Long): RentalBusiness? {
        return businesses.find { b -> b.id == id }
    }
}