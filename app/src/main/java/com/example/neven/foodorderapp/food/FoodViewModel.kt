package com.example.neven.foodorderapp.food

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FoodViewModel @Inject constructor(val repo: FoodRepositoryImpl, val compositeDisposable: CompositeDisposable) : ViewModel() {

    var liveDataFood: LiveData<Food>? = null

    fun getFoodLiveData(): LiveData<Food>? {
        return liveDataFood
    }

    fun loadData() {
        liveDataFood = repo.getFood()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}