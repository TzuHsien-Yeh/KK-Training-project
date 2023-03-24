package com.example.sampleproject.feature_travel.data.source

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.model.AttractionDto

interface TravelDataSource {
    suspend fun getAttractions(lang: String, page: Int): Resource<AttractionDto>
}