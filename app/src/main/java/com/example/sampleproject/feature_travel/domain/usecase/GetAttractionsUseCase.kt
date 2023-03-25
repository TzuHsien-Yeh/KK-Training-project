package com.example.sampleproject.feature_travel.domain.usecase

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.model.AttractionList
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
import java.util.*
import javax.inject.Inject

class GetAttractionsUseCase @Inject constructor(
    private val travelRepository: TravelRepository
) {
    private var nextPage: Int = 0
    suspend operator fun invoke (): Resource<AttractionList> {
        nextPage++
        return travelRepository.getAttractionInfo(lang = Locale.getDefault().language, page = nextPage)

        // TODO: If time is enough, get user lang setting (default = system lang) from SharedPref.

    }

}