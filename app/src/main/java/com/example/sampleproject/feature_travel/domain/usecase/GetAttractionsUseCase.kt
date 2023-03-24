package com.example.sampleproject.feature_travel.domain.usecase

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
import java.util.*
import javax.inject.Inject

class GetAttractionsUseCase @Inject constructor(
    private val travelRepository: TravelRepository
) {

    suspend operator fun invoke (page: Int): Resource<List<Attraction>>{
        return travelRepository.getAttractionInfo(lang = Locale.getDefault().language, page = page)

    // TODO: If time is enough, get user lang setting (default = system lang) from SharedPref.

    }

}