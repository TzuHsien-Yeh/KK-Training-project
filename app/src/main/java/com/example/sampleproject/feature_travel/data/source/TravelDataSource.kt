package com.example.sampleproject.feature_travel.data.source

import androidx.lifecycle.LiveData
import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.model.AttractionDto
import com.example.sampleproject.feature_travel.domain.model.Attraction

interface TravelDataSource {
    suspend fun getAttractions(lang: String, page: Int): Resource<AttractionDto>

    fun getFavoriteAttractions(): LiveData<List<Attraction>>

    suspend fun addAttractionToFavorite(attraction: Attraction)

    suspend fun deleteAttractionFromFavorite(attraction: Attraction)
}