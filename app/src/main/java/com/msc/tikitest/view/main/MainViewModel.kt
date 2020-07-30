package com.msc.tikitest.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msc.tikitest.model.banner.BannerResponse
import com.msc.tikitest.model.hot_deal.HotDealResponse
import com.msc.tikitest.model.link.LinkResponse
import com.msc.tikitest.repository.UseCaseResult
import com.msc.tikitest.repository.banner.BannerRepository
import com.msc.tikitest.repository.deal.HotDealRepository
import com.msc.tikitest.repository.link.LinkRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel (
    private val bannerRepository: BannerRepository,
    private val linkRepository : LinkRepository,
    private val hotDealRepository: HotDealRepository
) : ViewModel(), CoroutineScope{
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    var loadingBanner = MutableLiveData<Boolean>()
    var bannerData = MutableLiveData<BannerResponse>()
    var errorBanner = MutableLiveData<String>()

    var loadingLink = MutableLiveData<Boolean>()
    var linkData = MutableLiveData<LinkResponse>()
    var errorLink= MutableLiveData<String>()

    var loadingHotDeal = MutableLiveData<Boolean>()
    var hotDealData = MutableLiveData<HotDealResponse>()
    var errorHotDeal= MutableLiveData<String>()

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

    fun getAllHotDeal(){
        launch {
            loadingHotDeal.value = true
            val result = withContext(Dispatchers.IO){
                hotDealRepository.getAllHotDeal()
            }
            loadingHotDeal.value = false
            when(result){
                is UseCaseResult.Success -> {
                    hotDealData.value = result.data
                }
                is UseCaseResult.Error -> {
                    errorHotDeal.value = result.errorMessage
                }
            }
        }
    }

}