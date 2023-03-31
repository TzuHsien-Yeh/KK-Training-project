package com.example.sampleproject.feature_travel.presentation.model

sealed class AttractionMedia {
    abstract val id: Int

    data class Video(val video: Int) : AttractionMedia() {
        override var id: Int = -1
    }

    data class Image(val image: String): AttractionMedia() {
        override var id: Int = -1
    }
}