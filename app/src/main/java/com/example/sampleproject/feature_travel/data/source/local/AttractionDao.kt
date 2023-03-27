package com.example.sampleproject.feature_travel.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sampleproject.feature_travel.domain.model.Attraction

@Dao
interface AttractionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(attraction: Attraction)

    @Delete
    suspend fun deleteFromFavorite(attraction: Attraction)

    @Query("SELECT * FROM favorite_attractions")
    fun getFavoriteAttractions(): LiveData<List<Attraction>>

}