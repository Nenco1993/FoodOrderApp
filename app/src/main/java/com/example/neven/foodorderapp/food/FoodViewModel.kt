package com.example.neven.foodorderapp.food

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.neven.foodorderapp.data.FoodRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FoodViewModel @Inject constructor(repo: FoodRepositoryImpl, val compositeDisposable: CompositeDisposable) : ViewModel() {

    var liveDataFood: LiveData<List<Meal>>? = null

    init {
        liveDataFood = repo.getFood()
    }

    fun getFoodLiveData(): LiveData<List<Meal>>? {
        return liveDataFood
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}