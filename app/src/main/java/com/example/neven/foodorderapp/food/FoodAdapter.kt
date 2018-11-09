package com.example.neven.foodorderapp.food

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.neven.foodorderapp.R
import com.example.neven.foodorderapp.databinding.ListItemFoodBinding

class FoodAdapter : RecyclerView.Adapter<FoodViewHolder>() {

    var listMeals: List<Meal> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding: ListItemFoodBinding = DataBindingUtil.inflate<ListItemFoodBinding>(LayoutInflater.from(parent.context), R.layout.list_item_food, parent, false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMeals.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.mBinding.meal = listMeals[position]
    }

    fun setData(food: Food?) {
        if (listMeals.isEmpty()) {
            listMeals = food?.meals!!
            notifyItemRangeInserted(0, listMeals.size)
        }
    }
}