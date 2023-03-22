package com.example.sampleproject.di

import com.example.sampleproject.BuildConfig
import com.example.sampleproject.feature_travel.data.source.remote.network.TravelApiService
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
object NetworkModule {

    private const val BASE_URL_TRAVEL_TAIPEI = "www.travel.taipei/open-api"

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    )

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val url = chain
                .request()
                .url
                .newBuilder()
                .build()
            chain.proceed(chain.request().newBuilder().url(url).build())
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL_TRAVEL_TAIPEI)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideTravelApiService(retrofit: Retrofit): TravelApiService = retrofit.create(
        TravelApiService::class.java)

}