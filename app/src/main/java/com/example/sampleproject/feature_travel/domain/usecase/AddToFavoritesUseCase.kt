package com.example.sampleproject.feature_travel.domain.usecase

import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val travelRepository: TravelRepository
) {

    suspend operator fun invoke(attraction: Attraction) {
        travelRepository.addAttractionToFavorite(attraction)
    }
}
