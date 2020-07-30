package com.msc.tikitest.model.banner

import com.google.gson.annotations.SerializedName

data class BannerDetailsResponse(
    val id: Long,
    val title: String,
    val content: String,
    val url: String,

    @SerializedName("image_url")
    val imageURL: String,

    @SerializedName("thumbnail_url")
    val thumbnailURL: String,

    @SerializedName("mobile_url")
    val mobileURL: String,

    val ratio: Double
)