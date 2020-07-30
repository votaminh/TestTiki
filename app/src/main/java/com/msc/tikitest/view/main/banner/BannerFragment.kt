package com.msc.tikitest.view.main.banner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.msc.tikitest.R
import com.msc.tikitest.model.banner.BannerDetailsResponse
import kotlinx.android.synthetic.main.fragment_banner.view.*

class BannerFragment : Fragment(){
    private var bannerItem : BannerDetailsResponse? = null
    var bannerClickListener : BannerClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_banner, container, false)

        bannerItem?.let {
            Glide.with(context!!).load(it.imageURL).apply(RequestOptions().override(0, 200)).into(view.imvBanner)
            view.imvBanner.setOnClickListener{
                bannerClickListener?.onBannerClick(bannerItem!!.title)
            }
        }
        return view
    }

    fun setBannerDetailsItem(element: BannerDetailsResponse) {
        bannerItem = element
    }
}