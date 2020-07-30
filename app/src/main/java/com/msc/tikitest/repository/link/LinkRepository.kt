package com.msc.tikitest.repository.link

import com.msc.tikitest.model.link.LinkResponse
import com.msc.tikitest.repository.UseCaseResult

interface LinkRepository {
    suspend fun getQuickLink() : UseCaseResult<LinkResponse>
}