package com.example.sampleproject.feature_travel.data.source.remote.network

import com.example.sampleproject.feature_travel.data.model.AttractionDto
import retrofit2.http.*

interface TravelApiService {

    @Headers("accept: application/json")
    @GET("{lang}$ENDPOINT_ATTRACTION$ENDPOINT_ALL")
    suspend fun getAttractions(
        @Path("lang") lang: String,
        @Query("page") page: Int
    ): AttractionDto

    companion object {
        const val ENDPOINT_ATTRACTION = "/Attractions"
        const val ENDPOINT_ALL = "/All"
    }
}