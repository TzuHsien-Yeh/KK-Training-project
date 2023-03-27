package com.example.sampleproject.feature_travel.domain.usecase

import androidx.core.graphics.rotationMatrix
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val travelRepository: TravelRepository
) {
    suspend operator fun invoke(attraction: Attraction) {
        travelRepository.deleteAttractionFromFavorite(attraction)
    }
}
