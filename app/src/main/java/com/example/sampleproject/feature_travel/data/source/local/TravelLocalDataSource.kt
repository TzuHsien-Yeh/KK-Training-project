package com.example.sampleproject.feature_travel.data.source.local

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.model.AttractionResponse
import com.example.sampleproject.feature_travel.data.source.TravelDataSource

class TravelLocalDataSource : TravelDataSource {
    override suspend fun getAttractions(page: Int): Resource<AttractionResponse> {
        TODO("Not yet implemented")
    }
}