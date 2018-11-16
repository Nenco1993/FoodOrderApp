package com.example.neven.foodorderapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(OrderDetails::class), version = 1, exportSchema = true)
abstract class ReceiptsDatabase : RoomDatabase() {
    abstract fun receiptDao(): ReceiptDao
}