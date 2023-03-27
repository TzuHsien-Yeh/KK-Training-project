package com.example.sampleproject.feature_travel.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attraction(
    val name: String,
    val image: Image,
    val address: String,
    val introduction: String,
    val openTime: String
    ): Parcelable