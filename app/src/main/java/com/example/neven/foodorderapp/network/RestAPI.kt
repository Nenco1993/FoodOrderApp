package com.example.neven.foodorderapp.network

import com.example.neven.foodorderapp.food.Food
import io.reactivex.Flowable
import retrofit2.http.GET

interface RestAPI {

    @GET("filter.php?i=beef")
    fun getFood(): Flowable<Food>

}