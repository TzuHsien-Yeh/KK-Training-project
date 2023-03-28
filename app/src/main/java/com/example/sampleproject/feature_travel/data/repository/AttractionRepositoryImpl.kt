package com.example.sampleproject.feature_travel.data.repository

import androidx.lifecycle.LiveData
import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.mapper.toAttractionList
import com.example.sampleproject.feature_travel.data.source.AttractionDataSource
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.model.AttractionList
import com.example.sampleproject.feature_travel.domain.repository.AttractionRepository
import javax.inject.Inject

class AttractionRepositoryImpl @Inject constructor(
    private val attractionRemoteDataSource: AttractionDataSource,
    private val attractionLocalDataSource: AttractionDataSource
): AttractionRepository {
    override suspend fun getAttractionInfo(lang: String, page: Int): Resource<AttractionList> {
        return when (val response = attractionRemoteDataSource.getAttractions(lang, page)){
            is Resource.Success -> {
                Resource.Success(response.data?.toAttractionList())
            }
            is Resource.Error -> {
                Resource.Error(response.message.orEmpty())
            }
        }
    }

    override fun getFavoriteAttractions(): LiveData<List<Attraction>> {
        return attractionLocalDataSource.getFavoriteAttractions()
    }

    override suspend fun addAttractionToFavorite(attraction: Attraction) {
        return attractionLocalDataSource.addAttractionToFavorite(attraction)
    }

    override suspend fun deleteAttractionFromFavorite(attraction: Attraction) {
        return attractionLocalDataSource.deleteAttractionFromFavorite(attraction)
    }
}