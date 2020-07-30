package com.msc.tikitest.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.msc.tikitest.R
import com.msc.tikitest.model.BannerDetailsResponse
import com.msc.tikitest.view.main.banner.BannerFragment
import com.msc.tikitest.view.main.banner.BannerPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        getViewModel(MainViewModel::class)
    }

    var bannerPagerAdapter : BannerPagerAdapter? = null
    var banners : List<BannerDetailsResponse> = ArrayList<BannerDetailsResponse>()

    var runnable : Runnable? = null
    var currentIndexBanner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListenerViewModel()
        viewModel.getAllBanner()
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