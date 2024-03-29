package com.example.sampleproject.feature_travel.data.source.local

import androidx.lifecycle.LiveData
import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.model.AttractionDto
import com.example.sampleproject.feature_travel.data.source.AttractionDataSource
import com.example.sampleproject.feature_travel.domain.model.Attraction
import javax.inject.Inject

class AttractionLocalDataSource @Inject constructor(
    private val attractionDao: AttractionDao
) : AttractionDataSource {
    override suspend fun getAttractions(lang: String, page: Int): Resource<AttractionDto> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteAttractions(): LiveData<List<Attraction>> {
        return attractionDao.getFavoriteAttractions()
    }

    override suspend fun addAttractionToFavorite(attraction: Attraction) {
        return attractionDao.insert(attraction)
    }

    override suspend fun deleteAttractionFromFavorite(attraction: Attraction) {
        return attractionDao.deleteFromFavorite(attraction)
    }
}