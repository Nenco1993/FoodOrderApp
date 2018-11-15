package com.example.neven.foodorderapp.data

import android.arch.lifecycle.LiveData
import com.example.neven.foodorderapp.food.Meal

interface FoodRepository {
    fun getFood(): LiveData<List<Meal>>
}