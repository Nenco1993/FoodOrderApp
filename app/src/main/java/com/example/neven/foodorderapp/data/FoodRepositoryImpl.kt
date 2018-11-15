package com.example.neven.foodorderapp.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.neven.foodorderapp.food.Meal
import com.example.neven.foodorderapp.network.RestAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(val restAPI: RestAPI, val compositeDisposable: CompositeDisposable) : FoodRepository {

    override fun getFood(): LiveData<List<Meal>> {
        val liveDataFood = MutableLiveData<List<Meal>>()
        val flowableFood = restAPI.getFood()
        val disposableFood: Disposable = flowableFood
                .subscribeOn(Schedulers.io())
                .map { it.meals }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            liveDataFood.value = it
                        },
                        {
                            it.printStackTrace()
                        }
                )
        compositeDisposable.add(disposableFood)
        return liveDataFood
    }
}