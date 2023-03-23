package com.example.sampleproject.feature_travel.data.source.remote

import com.example.sampleproject.R
import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.core.util.Util
import com.example.sampleproject.feature_travel.data.model.AttractionResponse
import com.example.sampleproject.feature_travel.data.source.TravelDataSource
import com.example.sampleproject.feature_travel.data.source.remote.network.TravelApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject



class TravelRemoteDataSource @Inject constructor(
    private val travelApiService: TravelApiService
) : TravelDataSource {
    override suspend fun getAttractions(page: Int): Resource<AttractionResponse> =
        withContext(Dispatchers.IO) {

            if (!Util.isInternetConnected()) {
                return@withContext Resource.Error(Util.getString(R.string.internet_not_connected))
            }

            try {
                val result = travelApiService.getAttractions("zh-tw",1)

                Timber.d("api result = ${result.data}")
                Resource.Success(result)

            } catch (e: HttpException) {
                Timber.w(" exception=${e.message}")
                when (e.code()) {
                    else -> Resource.Error(e.message())
                }
            }
        }

}