package com.example.sampleproject.feature_travel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.model.AttractionList
import com.example.sampleproject.feature_travel.domain.repository.AttractionRepository

class FakeAttractionRepository: AttractionRepository {

    // Attractions info fetched from api
    private val attractions = mutableListOf<Attraction>(
        Attraction(1, "a", "a", "a", "a", "a"),
        Attraction(2, "b", "b", "b", "b", "b"),
        Attraction(3, "c", "c", "c", "c", "c")
    )
    private val attractionList = AttractionList(attractions).also { it.total = 7 }

    // Attractions saved to my favorites
    val favorites = mutableListOf(Attraction(3, "c", "c", "c", "c", "c"))

    // Network error simulation
    private var shouldReturnNetworkError = false
    val networkErrorMsg = "NetworkError"
    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    // Repository methods
    override suspend fun getAttractionInfo(lang: String, page: Int): Resource<AttractionList> {
        return if (shouldReturnNetworkError) {
            Resource.Error(networkErrorMsg)
        } else {
            Resource.Success(attractionList)
        }
    }

    override fun getFavoriteAttractions(): LiveData<List<Attraction>> {
        return MutableLiveData(favorites)
    }

    override suspend fun addAttractionToFavorite(attraction: Attraction) {
        favorites.add(attraction)
    }

    override suspend fun deleteAttractionFromFavorite(attraction: Attraction) {
        favorites.remove(attraction)
    }

}