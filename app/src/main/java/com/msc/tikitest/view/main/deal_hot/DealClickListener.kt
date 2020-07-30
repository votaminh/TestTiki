package com.msc.tikitest.view.main.deal_hot

import com.msc.tikitest.model.hot_deal.DealDetailResponse

interface DealClickListener {
    fun onDealClick(deal : DealDetailResponse)
}