package com.msc.tikitest.repository.link

import com.msc.tikitest.model.link.LinkResponse
import com.msc.tikitest.remote.link.LinkService
import com.msc.tikitest.repository.UseCaseResult
import retrofit2.HttpException

class LinkRepositoryImp (private val linkService: LinkService) : LinkRepository{
    override suspend fun getQuickLink(): UseCaseResult<LinkResponse> {
        return try {
            val result = linkService.getQuickLinkAsync().await()
            UseCaseResult.Success(result)
        } catch (ex: Throwable) {
            val response = (ex as? HttpException)?.response()
            if(response != null){
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
            }else{
                UseCaseResult.Error("Error")
            }


        }
    }
}