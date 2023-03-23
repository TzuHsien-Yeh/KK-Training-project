package com.example.sampleproject.feature_travel.data.source

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.model.AttractionResponse

interface TravelDataSource {

    suspend fun getAttractions(page: Int): Resource<AttractionResponse>

}