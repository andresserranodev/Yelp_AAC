package com.puzzlebench.yelp_aac.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_table")
data class BusinessEntity constructor(
    @PrimaryKey var businessId: String,
    val name: String,
    val local: String,
    val imageUrl: String,
    val displayPhone: String,
    val displayAddress: String,
    val isClosed: Boolean,
    val rating: Float,
    val price: String
)
