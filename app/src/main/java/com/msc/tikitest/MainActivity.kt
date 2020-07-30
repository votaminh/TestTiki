package com.msc.tikitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.msc.tikitest.banner.BannerFragment
import com.msc.tikitest.banner.BannerPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var bannerPagerAdapter : BannerPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buildBanner()
    }

    private fun buildBanner() {
        var bannerFragments = ArrayList<Fragment>()
        for (i in 1..10){
            var bannerFragment = BannerFragment()
            bannerFragments.add(bannerFragment)
        }

        bannerPagerAdapter = BannerPagerAdapter(supportFragmentManager, bannerFragments)
        bannerPager.adapter = bannerPagerAdapter
    }
}