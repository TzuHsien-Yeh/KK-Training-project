package com.example.sampleproject.feature_travel.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

// Attractions with the same name and address are treated as the same items
@Entity(tableName = "favorite_attractions", primaryKeys = ["name", "address"])
@Parcelize
data class Attraction(
    val name: String,
    val image: String,
    val address: String,
    val introduction: String,
    val openTime: String
    ): Parcelable