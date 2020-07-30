package com.msc.tikitest.repository.banner

import com.msc.tikitest.model.banner.BannerResponse
import com.msc.tikitest.repository.UseCaseResult

interface BannerRepository {
    suspend fun getAllBanner() : UseCaseResult<BannerResponse>
}