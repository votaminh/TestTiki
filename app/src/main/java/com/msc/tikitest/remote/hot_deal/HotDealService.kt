package com.msc.tikitest.remote.hot_deal

import com.msc.tikitest.model.hot_deal.HotDealResponse
import com.msc.tikitest.model.link.LinkResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HotDealService {
    @GET("widget/deals/hot")
    fun getAllHotDeal() : Deferred<HotDealResponse>;
}