package com.example.neven.foodorderapp.network

import com.example.neven.foodorderapp.food.Food
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RestAPI {

    @GET("filter.php?i=beef")
    fun getFood(): Flowable<Food>

}