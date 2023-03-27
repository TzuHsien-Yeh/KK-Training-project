package com.example.sampleproject.feature_travel.data.repository

import androidx.lifecycle.LiveData
import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.mapper.toAttractionList
import com.example.sampleproject.feature_travel.data.source.TravelDataSource
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.model.AttractionList
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
import javax.inject.Inject

class TravelRepositoryImpl @Inject constructor(
    private val travelRemoteDataSource: TravelDataSource,
    private val travelLocalDataSource: TravelDataSource
): TravelRepository {
    override suspend fun getAttractionInfo(lang: String, page: Int): Resource<AttractionList> {
        return when (val response = travelRemoteDataSource.getAttractions(lang, page)){
            is Resource.Success -> {
                Resource.Success(response.data?.toAttractionList())
            }
            is Resource.Error -> {
                Resource.Error(response.message.orEmpty())
            }
        }
    }

    override fun getFavoriteAttractions(): LiveData<List<Attraction>> {
        return travelLocalDataSource.getFavoriteAttractions()
    }

    override suspend fun addAttractionToFavorite(attraction: Attraction) {
        return travelLocalDataSource.addAttractionToFavorite(attraction)
    }

    override suspend fun deleteAttractionFromFavorite(attraction: Attraction) {
        return travelLocalDataSource.deleteAttractionFromFavorite(attraction)
    }
}