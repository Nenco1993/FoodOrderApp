package com.example.neven.foodorderapp.food

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Food {

    @SerializedName("meals")
    @Expose
    var meals: List<Meal>? = null

}
