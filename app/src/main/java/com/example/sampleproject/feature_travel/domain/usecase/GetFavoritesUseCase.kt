package com.example.sampleproject.feature_travel.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val travelRepository: TravelRepository
) {
    operator fun invoke(): LiveData<List<Attraction>> {
        val favorites = travelRepository.getFavoriteAttractions()
        val fav = Transformations.map(favorites) {
            for (attraction in it) {
                attraction.isFavorite = true
            }
            it
        }
        return fav
    }
}
