package com.example.sampleproject.feature_travel.domain.usecase

import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.domain.model.AttractionList
import com.example.sampleproject.feature_travel.domain.repository.AttractionRepository
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class GetAttractionsUseCase @Inject constructor(
    private val attractionRepository: AttractionRepository
) {
    private var nextPage: Int = 0
    suspend operator fun invoke (): Resource<AttractionList> {

        nextPage++

        val systemLang = if (Locale.getDefault().language == "zh") {
            if (Locale.getDefault().country == "TW") "zh-tw" else "zh-cn"
        } else {
            Locale.getDefault().language
        }

        Timber.d("systemLang = $systemLang")

        return attractionRepository.getAttractionInfo(lang = systemLang, page = nextPage)

        // TODO: If time is enough, get user lang setting (default = system lang) from SharedPref.

    }

}