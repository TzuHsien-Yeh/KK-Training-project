package com.example.sampleproject.feature_travel.data.source.local

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.model.AttractionDto
import com.example.sampleproject.feature_travel.data.source.TravelDataSource

class TravelLocalDataSource : TravelDataSource {
    override suspend fun getAttractions(lang: String, page: Int): Resource<AttractionDto> {
        TODO("Not yet implemented")
    }
}