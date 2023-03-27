package com.example.sampleproject.feature_travel.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val ext: String,
    val src: String,
    val subject: String
): Parcelable