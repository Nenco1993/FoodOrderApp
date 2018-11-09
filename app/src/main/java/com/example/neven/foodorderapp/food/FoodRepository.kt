package com.example.neven.foodorderapp.food

import android.arch.lifecycle.LiveData
import io.reactivex.Observable

interface FoodRepository {
    fun getFood(): LiveData<Food>
}