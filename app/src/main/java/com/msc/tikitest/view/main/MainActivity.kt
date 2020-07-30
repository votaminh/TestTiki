package com.msc.tikitest.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msc.tikitest.R
import com.msc.tikitest.model.banner.BannerDetailsResponse
import com.msc.tikitest.model.hot_deal.DealDetailResponse
import com.msc.tikitest.model.link.DetailsLinkResponse
import com.msc.tikitest.view.main.banner.BannerFragment
import com.msc.tikitest.view.main.banner.BannerPagerAdapter
import com.msc.tikitest.view.main.deal_hot.DealClickListener
import com.msc.tikitest.view.main.deal_hot.DealHotAdapter
import com.msc.tikitest.view.main.link.LinkAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        getViewModel(MainViewModel::class)
    }

    var bannerPagerAdapter : BannerPagerAdapter? = null
    var banners : List<BannerDetailsResponse> = ArrayList<BannerDetailsResponse>()

    var linkAdapter : LinkAdapter? = null
    var links = ArrayList<DetailsLinkResponse>()

    var dealAdapter : DealHotAdapter? = null
    var deals = ArrayList<DealDetailResponse>()

    var runnable : Runnable? = null
    var currentIndexBanner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildReLink()
        buildReDeal()
        initListenerViewModel()


        viewModel.getAllBanner()
        viewModel.getQuickLink()
        viewModel.getAllHotDeal()
    }

    private fun buildReDeal() {
        dealAdapter = DealHotAdapter(deals)
        dealAdapter?.dealClickListener = object : DealClickListener {
            override fun onDealClick(deal: DealDetailResponse) {
                Toast.makeText(applicationContext, "Click " + deal.product.name, Toast.LENGTH_LONG).show()
            }

        }
        reDealHot.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
        reDealHot.adapter = dealAdapter
    }

    private fun initListenerViewModel() {
        viewModel.loadingBanner.observe(this, Observer {
            if(it) loadBanner.visibility = View.VISIBLE else loadBanner.visibility = View.GONE
        })
        viewModel.bannerData.observe(this, Observer {
            banners = it.data
            buildBanner()

        })
        viewModel.errorBanner.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        })



        viewModel.linkData.observe(this, Observer {
            mergeListLinkToOne(it.data)
        })
        viewModel.errorLink.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()

        })

        viewModel.loadingHotDeal.observe(this, Observer {
        })
        viewModel.hotDealData.observe(this, Observer {
            dealAdapter?.setData(it.data)
        })
        viewModel.errorHotDeal.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()

        })
    }


    private fun buildReLink() {
        linkAdapter = LinkAdapter(links)
        reLink.layoutManager = GridLayoutManager(applicationContext, 2, RecyclerView.HORIZONTAL, false)
        reLink.adapter = linkAdapter
    }

    private fun mergeListLinkToOne(data: List<List<DetailsLinkResponse>>) {

        var size1 = data[0].size - 1
        var size2 = data[1].size - 1

        for(i in 0..size1.coerceAtLeast(size2)){
            if(i < data[0].size && data[0][i] != null){
                links.add(data[0][i])
            }else{
                links.add(DetailsLinkResponse("", "", false, "", ""))
            }

            if(i < data[1].size && data[1][i] != null){
                links.add(data[1][i])
            }else{
                links.add(DetailsLinkResponse("", "", false, "", ""))
            }
        }
        linkAdapter?.setData(links)
    }


    private fun buildBanner() {
        var bannerFragments = ArrayList<Fragment>()
        for (element in banners){
            var bannerFragment = BannerFragment()
            bannerFragment.setBannerDetailsItem(element)
            bannerFragments.add(bannerFragment)
        }

        bannerPager.offscreenPageLimit = banners.size - 1
        bannerPagerAdapter = BannerPagerAdapter(supportFragmentManager, bannerFragments)
        bannerPager.adapter = bannerPagerAdapter

        loopSwipeBanner()
    }

    private fun loopSwipeBanner() {
        runnable = Runnable {

            if(currentIndexBanner == banners.size) currentIndexBanner = 0

            bannerPager.currentItem = currentIndexBanner
            currentIndexBanner ++
            Handler().postDelayed(runnable!!, 2000)
        }

        Handler().post(runnable!!)
    }
}