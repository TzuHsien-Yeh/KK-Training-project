package com.example.sampleproject.feature_travel.data.model

import com.google.gson.annotations.SerializedName

data class AttractionDataDto(
    val address: String,
    val category: List<Category>,
    val distric: String,
    val elong: Double,
    val email: String,
    val facebook: String,
    val fax: String,
    val files: List<Any>,
    val friendly: List<Any>,
    val id: Int,
    val images: List<Image>,
    val introduction: String,
    val links: List<Link>,
    val modified: String,
    val months: String,
    val name: String,
    @SerializedName("name_zh") val nameZh: Any,
    val nlat: Double,
    @SerializedName("official_site") val officialSite: String,
    @SerializedName("open_status") val openStatus: Int,
    @SerializedName("open_time") val openTime: String,
    val remind: String,
    val service: List<Service>,
    val staytime: String,
    val target: List<Target>,
    val tel: String,
    val ticket: String,
    val url: String,
    val zipcode: String
)