package com.example.neven.foodorderapp.data

import io.reactivex.Completable
import javax.inject.Inject

class DatabaseManager @Inject constructor(val receiptDao: ReceiptDao) {

    fun saveReceipt(orderDetails: OrderDetails):Long {
        return receiptDao.saveReceipt(orderDetails)
    }
}