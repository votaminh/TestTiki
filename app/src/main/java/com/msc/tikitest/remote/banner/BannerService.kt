package com.msc.tikitest.remote.banner

import com.msc.tikitest.model.BannerResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.POST

interface BannerService {
    @POST("home/banners/v2")
    fun getAllBanner() : Deferred<BannerResponse>
}