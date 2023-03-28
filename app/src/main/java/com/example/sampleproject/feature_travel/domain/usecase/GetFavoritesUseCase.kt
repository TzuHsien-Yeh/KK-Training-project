package com.example.sampleproject.feature_travel.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.repository.AttractionRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val attractionRepository: AttractionRepository
) {
    operator fun invoke(): LiveData<List<Attraction>> {
        val favorites = attractionRepository.getFavoriteAttractions()
        val fav = Transformations.map(favorites) { attractionList ->
            attractionList.forEach { it.isFavorite = true }
            attractionList
        }
        return fav
    }
}
