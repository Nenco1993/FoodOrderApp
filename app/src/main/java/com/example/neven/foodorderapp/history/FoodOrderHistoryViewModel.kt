package com.example.neven.foodorderapp.history

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.neven.foodorderapp.data.FoodRepository
import com.example.neven.foodorderapp.data.OrderDetails
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FoodOrderHistoryViewModel @Inject constructor(val repo: FoodRepository,
                                                    val compositeDisposable: CompositeDisposable) : ViewModel() {

    var liveDataHistory: MutableLiveData<List<OrderDetails>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun searchByAddress(address: String) {
        val disposable = repo.getOrdersByAddress(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            liveDataHistory.value = it
                        },
                        {
                            liveDataHistory.value = null
                            it.printStackTrace()
                        }
                )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}