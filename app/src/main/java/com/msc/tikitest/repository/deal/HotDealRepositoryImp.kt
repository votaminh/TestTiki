package com.msc.tikitest.repository.deal

import com.msc.tikitest.model.hot_deal.HotDealResponse
import com.msc.tikitest.remote.hot_deal.HotDealService
import com.msc.tikitest.repository.UseCaseResult
import retrofit2.HttpException

class HotDealRepositoryImp (val hotDealService: HotDealService) : HotDealRepository{
    override suspend fun getAllHotDeal(): UseCaseResult<HotDealResponse> {
        return try {
            val result = hotDealService.getAllHotDeal().await()
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