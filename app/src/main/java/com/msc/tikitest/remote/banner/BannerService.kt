package com.msc.tikitest.remote.banner

import com.msc.tikitest.model.banner.BannerResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST

interface BannerService {
    @GET("home/banners/v2")
    fun getAllBanner() : Deferred<BannerResponse>
}