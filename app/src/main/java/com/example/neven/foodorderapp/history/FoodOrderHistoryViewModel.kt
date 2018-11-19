package com.example.neven.foodorderapp.history

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import com.example.neven.foodorderapp.data.FoodRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FoodOrderHistoryViewModel @Inject constructor(val repo: FoodRepository,
                                                    val compositeDisposable: CompositeDisposable) : ViewModel() {


    @SuppressLint("CheckResult")
    fun searchByAddress(address: String) {
        repo.getOrdersByAddress(address)
                .subscribeOn(Schedulers.io())
                .flatMap { Flowable.fromIterable(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            println("item that was found is " + it.address)
                        },
                        {
                            it.printStackTrace()
                        }
                )


    }

}