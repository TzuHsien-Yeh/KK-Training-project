package com.example.sampleproject.feature_travel.data.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class AttractionConverter {

    private val gson = Gson()
    private val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type

    @TypeConverter
    fun petsFromJsonArray(json: String?): List<String?>? {
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun petsToJsonArray(list: List<String?>?): String? {
        return gson.toJson(list)
    }
}