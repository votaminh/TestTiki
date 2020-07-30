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
            val response = (ex as? HttpException)?.response()
            response?.let {
                var message = ""
                message = when {
                    response?.code()!! > 500 -> {
                        "Server errors"
                    }
                    response?.code()!! > 400 -> {
                        "Connection error"
                    }
                    else -> {
                        "Redirects"
                    }
                }
                UseCaseResult.Error(message)
            }

            UseCaseResult.Error("Error")
        }
    }

}