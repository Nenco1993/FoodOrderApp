package com.example.neven.foodorderapp.food

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.support.v4.view.ViewCompat
import com.example.neven.foodorderapp.network.RestAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(val restAPI: RestAPI, val compositeDisposable: CompositeDisposable) : FoodRepository {

    override fun getFood(): LiveData<Food> {
        val liveDataFood = MutableLiveData<Food>()
        val observableFood = restAPI.getFood()
        val disposableFood: Disposable = observableFood.subscribeOn(Schedulers.io()).cache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            liveDataFood.value = it
                        },
                        {
                            liveDataFood.value = null
                            it.printStackTrace()
                        }
                )
        compositeDisposable.add(disposableFood)
        return liveDataFood
    }
}