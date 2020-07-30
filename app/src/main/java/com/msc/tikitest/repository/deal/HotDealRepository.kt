package com.msc.tikitest.repository.deal

import com.msc.tikitest.model.hot_deal.HotDealResponse
import com.msc.tikitest.repository.UseCaseResult

interface HotDealRepository {
    suspend fun getAllHotDeal() : UseCaseResult<HotDealResponse>
}