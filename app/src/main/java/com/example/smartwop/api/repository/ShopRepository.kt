package com.example.smartwop.api.repository

import androidx.lifecycle.MutableLiveData
import com.example.smartwop.api.NetworkManager
import com.example.smartwop.model.BaseResponseModel
import com.example.smartwop.model.CategoryModel
import com.example.smartwop.model.OffersModel
import com.example.smartwop.model.ProductModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
    class ShopRepository {
        val compositeDisposable = CompositeDisposable()
        fun getOffers(error: MutableLiveData<String>, success: MutableLiveData<List<OffersModel>>){
            compositeDisposable.add(
                NetworkManager.getApiService().getOffers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<BaseResponseModel<List<OffersModel>>>(){
                        override fun onNext(t: BaseResponseModel<List<OffersModel>>) {
                            if (t.success){
                                success.value = t.data
                            }else{
                                error.value = t.message
                            }
                        }

                        override fun onError(e: Throwable) {
                            error.value = e.localizedMessage
                        }

                        override fun onComplete() {
                        }
                    })
            )
        }

        fun getCategories(error: MutableLiveData<String>, success: MutableLiveData<List<CategoryModel>>){
            compositeDisposable.add(
                NetworkManager.getApiService().getCategories()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<BaseResponseModel<List<CategoryModel>>>(){
                        override fun onNext(t: BaseResponseModel<List<CategoryModel>>) {
                            if (t.success){
                                success.value = t.data
                            }else{
                                error.value = t.message
                            }
                        }

                        override fun onError(e: Throwable) {
                            error.value = e.localizedMessage
                        }

                        override fun onComplete() {
                        }
                    })
            )
        }
        fun getTopProducts(error: MutableLiveData<String>, success: MutableLiveData<List<ProductModel>>){
            compositeDisposable.add(
                NetworkManager.getApiService().getTopProducts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<BaseResponseModel<List<ProductModel>>>(){
                        override fun onNext(t: BaseResponseModel<List<ProductModel>>) {
                            if (t.success){
                                success.value = t.data
                            }else{
                                error.value = t.message
                            }
                        }

                        override fun onError(e: Throwable) {
                            error.value = e.localizedMessage
                        }

                        override fun onComplete() {
                        }
                    })
            )
        }
        fun getProductsByCategory(id: Int, error: MutableLiveData<String>, success: MutableLiveData<List<ProductModel>>){
            compositeDisposable.add(
                NetworkManager.getApiService().getCategoryProducts(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<BaseResponseModel<List<ProductModel>>>(){
                        override fun onNext(t: BaseResponseModel<List<ProductModel>>) {
                            if (t.success){
                                success.value = t.data
                            }else{
                                error.value = t.message
                            }
                        }

                        override fun onError(e: Throwable) {
                            error.value = e.localizedMessage
                        }

                        override fun onComplete() {
                        }
                    })
            )
        }
    }
