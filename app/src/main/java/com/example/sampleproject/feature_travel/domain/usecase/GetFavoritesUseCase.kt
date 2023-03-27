package com.example.sampleproject.feature_travel.domain.usecase

import androidx.lifecycle.LiveData
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val travelRepository: TravelRepository
) {
    operator fun invoke(): LiveData<List<Attraction>> {
        return travelRepository.getFavoriteAttractions()
    }
}
