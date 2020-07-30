package com.msc.tikitest.model.hot_deal

import com.google.gson.annotations.SerializedName

data class HotDealResponse (
    val dealDetailResponses: List<DealDetailResponse>,
    val tabs: List<Tab>,
    val version: String
)
data class DealDetailResponse (
    val status: Long,
    val url: String,
    val tags: String,

    @SerializedName("discount_percent")
    val discountPercent: Long,

    @SerializedName("special_price")
    val specialPrice: Long,

    @SerializedName("special_from_date")
    val specialFromDate: Long,

    @SerializedName("from_date")
    val fromDate: String,

    @SerializedName("special_to_date")
    val specialToDate: Long,

    val progress: Progress,
    val product: Product
)

data class Product (
    val id: Long,
    val sku: Any? = null,
    val name: String,

    @SerializedName("url_path")
    val urlPath: String,

    val price: Long,

    @SerializedName("list_price")
    val listPrice: Long,
    val discount: Long,

    @SerializedName("rating_average")
    val ratingAverage: Long,

    @SerializedName("review_count")
    val reviewCount: Long,

    @SerializedName("order_count")
    val orderCount: Long,

    @SerializedName("thumbnail_url")
    val thumbnailURL: String,

    @SerializedName("is_visible")
    val isVisible: Boolean,

    @SerializedName("is_fresh")
    val isFresh: Boolean,

    @SerializedName("is_flower")
    val isFlower: Boolean,

    @SerializedName("is_gift_card")
    val isGiftCard: Boolean,

    val inventory: Inventory,

    @SerializedName("url_attendant_input_form")
    val urlAttendantInputForm: String,

    @SerializedName("master_id")
    val masterID: Long,

    @SerializedName("seller_product_id")
    val sellerProductID: Long,

    @SerializedName("price_prefix")
    val pricePrefix: String
)
data class Inventory (
    @SerializedName("product_virtual_type")
    val productVirtualType: Any? = null,

    @SerializedName("fulfillment_type")
    val fulfillmentType: String
)

data class Progress (
    val qty: Long,

    @SerializedName("qty_ordered")
    val qtyOrdered: Long,

    @SerializedName("qty_remain")
    val qtyRemain: Long,

    val percent: Double,
    val status: String
)

data class Tab (
    @SerializedName("query_value")
    val queryValue: Long,

    @SerializedName("from_date")
    val fromDate: String,

    @SerializedName("to_date")
    val toDate: String,

    val display: String,
    val active: Boolean
)
