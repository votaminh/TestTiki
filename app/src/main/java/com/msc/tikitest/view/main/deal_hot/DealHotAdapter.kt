package com.msc.tikitest.view.main.deal_hot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.msc.tikitest.R
import com.msc.tikitest.model.hot_deal.DealDetailResponse
import kotlinx.android.synthetic.main.item_deal_hot.view.*
import java.text.DecimalFormat


class DealHotAdapter (private var deal : List<DealDetailResponse>) :
    RecyclerView.Adapter<DealHotAdapter.ViewHolder>() {
    var dealClickListener : DealClickListener? = null

    var context : Context? = null

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_deal_hot, parent, false)
        return ViewHolder(view = view)
    }

    override fun getItemCount(): Int {
        return deal.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dealDetailResponse = deal[position]
        holder.itemView.tvDiscount.text = "-"+dealDetailResponse.discountPercent+"%"
        holder.itemView.tvPrice.text = convertPrice(dealDetailResponse.product.price) + " đ"
        Glide.with(context!!).load(dealDetailResponse.product.thumbnailURL).into(holder.itemView.imvProduct)

        holder.itemView.imvProduct.setOnClickListener{
            dealClickListener?.onDealClick(dealDetailResponse)
        }

        val productOrder = dealDetailResponse.progress.qtyOrdered.toFloat()
        val productRemain = dealDetailResponse.progress.qtyRemain.toFloat()
        if( productOrder != 0f){
            holder.itemView.tvState.text = "Đã bán " + productOrder
            holder.itemView.progress.progress = (productOrder/(productOrder+productRemain)*10f)
        }else{
            holder.itemView.tvState.text = "Vừa mở bán"
        }
    }

    fun setData(l: List<DealDetailResponse>) {
        deal = l
        notifyDataSetChanged()
    }

    private fun convertPrice(price : Long) : String{
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(price)
    }

}