package com.example.sampleproject.feature_travel.domain.mapper

import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.presentation.model.AttractionMedia

fun Attraction.toAttractionMedia(): List<AttractionMedia> {

    val list = mutableListOf<AttractionMedia>()

    // add the video
    list.add(AttractionMedia.Video(this.video).also { it.id = this.id })

    // add images
    for (i in this.images) { list.add(AttractionMedia.Image(i).also { it.id = this.id }) }

    return list
}