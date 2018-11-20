package com.example.neven.foodorderapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderDetails(

        @ColumnInfo(name = "full_name")
        var fullName: String?,

        val address: String?,
        var quantity: String?,
        var mealName:String?) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}






