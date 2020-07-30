package com.msc.tikitest.view.main.link

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.internal.LinkedTreeMap
import com.msc.tikitest.R
import com.msc.tikitest.model.link.DetailsLinkResponse
import kotlinx.android.synthetic.main.item_link.view.*
import java.lang.Exception


class LinkAdapter (private var links : List<DetailsLinkResponse>) :
    RecyclerView.Adapter<LinkAdapter.ViewHolder>() {

    var linkClickListener : LinkClickListener? = null
    var context : Context? = null

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_link, parent, false)
        return ViewHolder(view = view)
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val getrow: Any = this.links[position]
            val t: LinkedTreeMap<*, *> = getrow as LinkedTreeMap<*, *>

            Glide.with(context!!).load(t["image_url"].toString()).into(holder.itemView.imvLink)
            holder.itemView.titleLink.text = t["title"].toString()

            holder.itemView.imvLink.setOnClickListener{
                linkClickListener?.onLinkClick(t["title"].toString())
            }
        }catch (e : Exception){
            holder.itemView.visibility = View.INVISIBLE
        }
    }

    fun setData(l: List<DetailsLinkResponse>) {
        links = l
        notifyDataSetChanged()
    }

}