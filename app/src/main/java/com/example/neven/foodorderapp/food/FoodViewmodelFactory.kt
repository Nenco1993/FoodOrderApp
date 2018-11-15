package com.example.neven.foodorderapp.food

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.neven.foodorderapp.data.FoodRepositoryImpl
import com.example.neven.foodorderapp.order.FoodOrderViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class FoodViewmodelFactory @Inject constructor(val repo: FoodRepositoryImpl, val compositeDisposable: CompositeDisposable) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(repo, compositeDisposable) as T
        }
        if (modelClass.isAssignableFrom(FoodOrderViewModel::class.java)) {
            return FoodOrderViewModel(repo, compositeDisposable) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}