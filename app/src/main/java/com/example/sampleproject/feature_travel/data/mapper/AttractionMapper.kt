package com.example.sampleproject.feature_travel.data.mapper

import com.example.sampleproject.R
import com.example.sampleproject.feature_travel.data.model.AttractionDto
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.model.AttractionList

fun AttractionDto.toAttractionList(): AttractionList {
    val list = mutableListOf<Attraction>()

    for(attraction in this.data){

        val attractionToDisplay = Attraction(
            id = attraction.id,
            name = attraction.name,
            image = attraction.images.firstOrNull()?.src ?: "",
            address = attraction.address,
            introduction = attraction.introduction,
            openTime = attraction.openTime,
            video = R.raw.video
        )

        list.add(attractionToDisplay)
    }

    val attractionList = AttractionList(list)
    attractionList.total = this.total

    return attractionList
}