package com.example.sampleproject.feature_travel.domain.usecase

import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.repository.AttractionRepository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val attractionRepository: AttractionRepository
) {
    suspend operator fun invoke(attraction: Attraction) {
        attractionRepository.deleteAttractionFromFavorite(attraction)
    }
}
