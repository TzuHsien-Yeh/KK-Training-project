package com.example.sampleproject.di

import android.app.Application
import androidx.room.Room
import com.example.sampleproject.BuildConfig
import com.example.sampleproject.feature_travel.data.repository.TravelRepositoryImpl
import com.example.sampleproject.feature_travel.data.source.TravelDataSource
import com.example.sampleproject.feature_travel.data.source.local.AttractionDao
import com.example.sampleproject.feature_travel.data.source.local.AttractionDatabase
import com.example.sampleproject.feature_travel.data.source.local.TravelLocalDataSource
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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("travelRemoteDataSource")
    fun provideTravelRemoteDataSource(
        travelApiService: TravelApiService
    ): TravelDataSource = TravelRemoteDataSource(travelApiService)

    @Provides
    @Singleton
    fun provideAttractionDatabase(app: Application): AttractionDatabase {
        return Room.databaseBuilder(
            app,
            AttractionDatabase::class.java,
            AttractionDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    @Named("travelLocalDataSource")
    fun provideTravelLocalDataSource(
        db: AttractionDatabase
    ): TravelDataSource = TravelLocalDataSource(db.attractionDao)

    @Provides
    @Singleton
    fun provideTravelRepository(
        @Named("travelRemoteDataSource") travelRemoteDataSource: TravelDataSource,
        @Named("travelLocalDataSource") travelLocalDataSource: TravelDataSource
    ): TravelRepository = TravelRepositoryImpl(
        travelRemoteDataSource = travelRemoteDataSource,
        travelLocalDataSource = travelLocalDataSource
    )
}