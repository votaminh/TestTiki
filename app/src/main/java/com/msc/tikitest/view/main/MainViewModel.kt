package com.msc.tikitest.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msc.tikitest.model.banner.BannerResponse
import com.msc.tikitest.model.link.LinkResponse
import com.msc.tikitest.repository.UseCaseResult
import com.msc.tikitest.repository.banner.BannerRepository
import com.msc.tikitest.repository.link.LinkRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel (
    private val bannerRepository: BannerRepository,
    private val linkRepository : LinkRepository
) : ViewModel(), CoroutineScope{
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    var loadingBanner = MutableLiveData<Boolean>()
    var bannerData = MutableLiveData<BannerResponse>()
    var errorBanner = MutableLiveData<String>()

    var loadingLink = MutableLiveData<Boolean>()
    var linkData = MutableLiveData<LinkResponse>()
    var errorLink= MutableLiveData<String>()

    fun getAllBanner(){
        launch {
            loadingBanner.value = true
            val result = withContext(Dispatchers.IO){
                bannerRepository.getAllBanner()
            }
            loadingBanner.value = false
            when(result){
                is UseCaseResult.Success -> {
                    bannerData.value = result.data
                }
                is UseCaseResult.Error -> {
                    errorBanner.value = result.errorMessage
                }
            }
        }
    }

    fun getQuickLink(){
        launch {
            loadingLink.value = true
            val result = withContext(Dispatchers.IO){
                linkRepository.getQuickLink()
            }
            loadingLink.value = false
            when(result){
                is UseCaseResult.Success -> {
                    linkData.value = result.data
                }
                is UseCaseResult.Error -> {
                    errorLink.value = result.errorMessage
                }
            }
        }
    }

}