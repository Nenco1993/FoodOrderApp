package com.example.neven.foodorderapp.data

import com.example.neven.foodorderapp.food.Food
import io.reactivex.Completable
import io.reactivex.Flowable

interface FoodRepository {
    fun getFood(): Flowable<Food>
    fun saveReceipt(orderDetails: OrderDetails):Long

}