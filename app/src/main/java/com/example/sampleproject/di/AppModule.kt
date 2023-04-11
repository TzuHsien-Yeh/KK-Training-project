package com.example.sampleproject.di

import android.app.Application
import androidx.room.Room
import com.example.sampleproject.feature_travel.data.repository.AttractionRepositoryImpl
import com.example.sampleproject.feature_travel.data.source.AttractionDataSource
import com.example.sampleproject.feature_travel.data.source.local.AttractionDatabase
import com.example.sampleproject.feature_travel.data.source.local.AttractionLocalDataSource
import com.example.sampleproject.feature_travel.data.source.remote.AttractionRemoteDataSource
import com.example.sampleproject.feature_travel.data.source.remote.network.TravelApiService
import com.example.sampleproject.feature_travel.domain.repository.AttractionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val ATTRACTION_REMOTE_DATA_SOURCE = "attractionRemoteDataSource"
    private const val ATTRACTION_LOCAL_DATA_SOURCE = "attractionLocalDataSource"

    @Provides
    @Singleton
    @Named(ATTRACTION_REMOTE_DATA_SOURCE)
    fun provideAttractionRemoteDataSource(
        travelApiService: TravelApiService
    ): AttractionDataSource = AttractionRemoteDataSource(travelApiService)

    @Provides
    @Singleton
    fun provideAttractionDatabase(app: Application): AttractionDatabase {
        return Room.databaseBuilder(
            app,
            AttractionDatabase::class.java,
            AttractionDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    @Named(ATTRACTION_LOCAL_DATA_SOURCE)
    fun provideAttractionLocalDataSource(
        db: AttractionDatabase
    ): AttractionDataSource = AttractionLocalDataSource(db.attractionDao)

    @Provides
    @Singleton
    fun provideAttractionRepository(
        @Named(ATTRACTION_REMOTE_DATA_SOURCE) attractionRemoteDataSource: AttractionDataSource,
        @Named(ATTRACTION_LOCAL_DATA_SOURCE) attractionLocalDataSource: AttractionDataSource
    ): AttractionRepository = AttractionRepositoryImpl(
        attractionRemoteDataSource = attractionRemoteDataSource,
        attractionLocalDataSource = attractionLocalDataSource
    )
}