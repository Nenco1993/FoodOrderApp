package com.example.neven.foodorderapp.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.neven.foodorderapp.food.Food
import com.example.neven.foodorderapp.food.Meal
import com.example.neven.foodorderapp.network.RestAPI
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(val restAPI: RestAPI) : FoodRepository {

    override fun getFood(): Flowable<Food> {
        return restAPI.getFood()
    }
}