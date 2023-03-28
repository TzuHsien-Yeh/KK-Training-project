package com.example.sampleproject.feature_travel.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

// Attractions with the same name and address are treated as the same items
@Entity(tableName = "favorite_attractions")
@Parcelize
data class Attraction(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val address: String,
    val introduction: String,
    val openTime: String
    ): Parcelable {
        @IgnoredOnParcel
        var isFavorite = false
    }