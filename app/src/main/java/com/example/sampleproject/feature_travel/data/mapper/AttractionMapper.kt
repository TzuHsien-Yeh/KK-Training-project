package com.example.sampleproject.feature_travel.data.mapper

import com.example.sampleproject.feature_travel.data.model.AttractionDto
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.model.Image

fun AttractionDto.toAttractionList(): List<Attraction> {
    val list = mutableListOf<Attraction>()

    for(attraction in this.data){
        val firstImage = Image(
            ext = attraction.images.firstOrNull()?.ext ?: "",
            src = attraction.images.firstOrNull()?.src ?: "",
            subject = attraction.images.firstOrNull()?.subject ?: ""
        )

        val attractionToDisplay = Attraction(
            name = attraction.name,
            image = firstImage,
            address = attraction.address,
            introduction = attraction.introduction
        )

        list.add(attractionToDisplay)
    }

    return list
}