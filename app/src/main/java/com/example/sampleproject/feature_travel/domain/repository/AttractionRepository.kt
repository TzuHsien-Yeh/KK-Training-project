package com.example.sampleproject.feature_travel.domain.repository

import androidx.lifecycle.LiveData
import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.model.AttractionList

interface AttractionRepository {
    suspend fun getAttractionInfo(lang: String, page: Int): Resource<AttractionList>

    fun getFavoriteAttractions(): LiveData<List<Attraction>>

    suspend fun addAttractionToFavorite(attraction: Attraction)

    suspend fun deleteAttractionFromFavorite(attraction: Attraction)
}