package com.example.sampleproject.feature_travel.domain.repository

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.domain.model.Attraction

interface TravelRepository {
    suspend fun getAttractionInfo(lang: String, page: Int): Resource<List<Attraction>>
}