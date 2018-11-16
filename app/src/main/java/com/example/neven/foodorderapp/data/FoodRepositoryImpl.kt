package com.example.neven.foodorderapp.data

import android.provider.ContactsContract
import com.example.neven.foodorderapp.food.Food
import com.example.neven.foodorderapp.network.RestAPI
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(val restAPI: RestAPI, val dbManager: DatabaseManager) : FoodRepository {

    override fun getFood(): Flowable<Food> {
        return restAPI.getFood()
    }

    override fun saveReceipt(orderDetails: OrderDetails):Long {
        return dbManager.saveReceipt(orderDetails)
    }
}