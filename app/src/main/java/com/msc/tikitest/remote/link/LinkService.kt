package com.msc.tikitest.remote.link

import com.msc.tikitest.model.banner.BannerResponse
import com.msc.tikitest.model.link.LinkResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST

interface LinkService {
    @GET("widgets/quick_link")
    fun getQuickLinkAsync() : Deferred<LinkResponse>
}