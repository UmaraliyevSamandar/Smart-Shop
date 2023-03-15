package com.example.smartwop.api

import android.database.Observable
import com.example.smartwop.model.BaseResponseModel
import com.example.smartwop.model.CategoryModel
import com.example.smartwop.model.OffersModel
import com.example.smartwop.model.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("get_offers")
    fun getOffers(): io.reactivex.Observable<BaseResponseModel<List<OffersModel>>>

    @GET("get_categories")
    fun getCategories(): io.reactivex.Observable<BaseResponseModel<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts(): io.reactivex.Observable<BaseResponseModel<List<ProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id") categoryId: Int): io.reactivex.Observable<BaseResponseModel<List<ProductModel>>>
}