package com.msc.tikitest.model.link

import com.google.gson.annotations.SerializedName

data class DetailsLinkResponse(
    val title: String? ,
    val url: String?,
    val authentication: Boolean?,

    @SerializedName("web_url")
    val webURL: String? ,

    @SerializedName("image_url")
    val imageURL: String?
)

