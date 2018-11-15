package com.example.neven.foodorderapp.order

import android.arch.lifecycle.ViewModel
import com.example.neven.foodorderapp.data.FoodRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FoodOrderViewModel @Inject constructor(val repo:FoodRepository,val compositeDisposable: CompositeDisposable) : ViewModel() {

    init {

    }




}