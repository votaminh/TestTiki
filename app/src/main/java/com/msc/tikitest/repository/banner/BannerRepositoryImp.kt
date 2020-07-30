package com.msc.tikitest.repository.banner

import com.google.gson.Gson
import com.msc.tikitest.model.BannerResponse
import com.msc.tikitest.remote.banner.BannerService
import com.msc.tikitest.repository.UseCaseResult
import org.json.JSONObject
import retrofit2.HttpException

class BannerRepositoryImp (private val bannerService: BannerService) : BannerRepository{
    override suspend fun getAllBanner(): UseCaseResult<BannerResponse> {
        return try {
            val result = bannerService.getAllBanner().await()
            UseCaseResult.Success(result)
        } catch (ex: Throwable) {
            UseCaseResult.Error(ex.message ?: "")
        }
    }

}