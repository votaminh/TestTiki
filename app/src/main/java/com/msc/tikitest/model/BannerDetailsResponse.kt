package com.msc.tikitest.model

data class BannerDetailsResponse(
    var id : Int,
    var title : String,
    var url : String,
    var image_url : String,
    var mobile_url : String,
    var ratio : Float
)