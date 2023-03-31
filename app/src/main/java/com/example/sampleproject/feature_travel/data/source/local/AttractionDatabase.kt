package com.example.sampleproject.feature_travel.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampleproject.feature_travel.domain.model.Attraction

@Database(entities = [Attraction::class], version = 3)
abstract class AttractionDatabase: RoomDatabase() {
    abstract val attractionDao: AttractionDao

    companion object {
        const val DATABASE_NAME = "attractions_db"
    }
}