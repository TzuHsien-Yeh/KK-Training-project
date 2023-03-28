package com.example.sampleproject.feature_travel.domain.usecase

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.domain.model.AttractionList
import com.example.sampleproject.feature_travel.domain.repository.AttractionRepository
import java.util.*
import javax.inject.Inject

class GetAttractionsUseCase @Inject constructor(
    private val attractionRepository: AttractionRepository
) {
    private var nextPage: Int = 0
    suspend operator fun invoke (): Resource<AttractionList> {
        nextPage++
        return attractionRepository.getAttractionInfo(lang = Locale.getDefault().language, page = nextPage)

        // TODO: If time is enough, get user lang setting (default = system lang) from SharedPref.

    }

}