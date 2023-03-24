package com.example.sampleproject.di

import com.example.sampleproject.BuildConfig
import com.example.sampleproject.feature_travel.data.repository.TravelRepositoryImpl
import com.example.sampleproject.feature_travel.data.source.TravelDataSource
import com.example.sampleproject.feature_travel.data.source.remote.TravelRemoteDataSource
import com.example.sampleproject.feature_travel.data.source.remote.network.TravelApiService
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTravelRemoteDataSource(
        travelApiService: TravelApiService
    ): TravelDataSource = TravelRemoteDataSource(travelApiService)

    @Provides
    @Singleton
    fun provideTravelRepository(
        travelDataSource: TravelDataSource
    ): TravelRepository = TravelRepositoryImpl(travelDataSource)
}