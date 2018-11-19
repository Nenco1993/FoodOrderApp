package com.example.neven.foodorderapp.data

import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class DatabaseManager @Inject constructor(val receiptDao: ReceiptDao) {

    fun saveReceipt(orderDetails: OrderDetails): Long {
        return receiptDao.saveReceipt(orderDetails)
    }

    fun getAllReceipts(): Flowable<List<OrderDetails>> {
        return receiptDao.getAllReceipts()
    }

    fun getOrdersByAddress(address: String): Flowable<List<OrderDetails>> {
        val searchAddress = address + "%"
        return receiptDao.searchByAddress(searchAddress)
    }
}