package com.example.neven.foodorderapp.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ReceiptDao {

    @Query("SELECT * FROM orders WHERE id=:id")
    fun getReceiptById(id: Int): Flowable<OrderDetails>

    @Query("SELECT * FROM orders")
    fun getAllReceipts(): Flowable<List<OrderDetails>>

    @Insert
    fun saveReceipt(orderDetails: OrderDetails): Long

    @Query("SELECT * FROM orders WHERE address LIKE :address ORDER BY full_name ASC")
    fun searchByAddress(address: String): Flowable<List<OrderDetails>>

}