package com.example.sampleproject.feature_travel.domain.model

import android.os.Parcelable
import androidx.annotation.RawRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sampleproject.feature_travel.data.typeconverter.AttractionConverter
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@TypeConverters(AttractionConverter::class)
@Entity(tableName = "favorite_attractions")
@Parcelize
data class Attraction(
    @PrimaryKey val id: Int,
    val name: String,
    val images: List<String>,
    val address: String,
    val introduction: String,
    val openTime: String,
    @RawRes val video: Int
) : Parcelable {
    @IgnoredOnParcel
    var isFavorite = false
}