package com.example.smartwop.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartwop.api.repository.ShopRepository
import com.example.smartwop.model.CategoryModel
import com.example.smartwop.model.OffersModel
import com.example.smartwop.model.ProductModel

class MainViewModel: ViewModel() {
    val repository = ShopRepository()
    val error = MutableLiveData<String>()
    val categoriesData = MutableLiveData<List<CategoryModel>>()
    val offersData = MutableLiveData<List<OffersModel>>()
    val productData = MutableLiveData<List<ProductModel>>()

    fun getOffers() {
        repository.getOffers(error, offersData)
    }

    fun getTopProducts() {
        repository.getTopProducts(error, productData)
    }

    fun getCategories() {
        repository.getCategories(error, categoriesData)
    }

    fun getProductsByCategory(id: Int) {
        repository.getProductsByCategory(id, error, productData)
    }
}
