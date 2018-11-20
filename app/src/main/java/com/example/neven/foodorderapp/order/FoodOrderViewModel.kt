package com.example.neven.foodorderapp.order

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.provider.Telephony
import com.example.neven.foodorderapp.data.FoodRepository
import com.example.neven.foodorderapp.data.OrderDetails
import com.example.neven.foodorderapp.food.FoodConstants
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FoodOrderViewModel @Inject constructor(val repo: FoodRepository, val compositeDisposable: CompositeDisposable) : ViewModel() {

    val responseMessage = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun submitOrder(orderDetails: OrderDetails) {
        val single = Single.create<Long> { emitter ->
            emitter.onSuccess(repo.saveReceipt(orderDetails))
        }
        val disposable = single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            responseMessage.value = FoodConstants.RESPONSE_ORDER_OK
                        },
                        {
                            it.printStackTrace()
                            responseMessage.value = FoodConstants.RESPONSE_ORDER_ERROR
                        }
                )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}