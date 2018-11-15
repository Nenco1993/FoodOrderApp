package com.example.neven.foodorderapp.food

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.neven.foodorderapp.R
import com.example.neven.foodorderapp.databinding.ListItemFoodBinding
import com.jakewharton.rxbinding.view.RxView

class FoodAdapter : RecyclerView.Adapter<FoodViewHolder>() {

    var listMeals: List<Meal> = ArrayList()
    lateinit var listener: OnFoodClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding: ListItemFoodBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_food, parent, false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMeals.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val singleMeal = listMeals[position]
        holder.mBinding.meal = singleMeal
        RxView.clicks(holder.itemView)
                .subscribe {
                    listener.onFoodClicked(singleMeal)
                }
    }

    fun setData(meals: List<Meal>?) {
        if (listMeals.isEmpty()) {
            meals?.let {
                listMeals = it
                notifyDataSetChanged()
            }
        }
    }

    fun setOnFoodClickListener(listener: OnFoodClickListener) {
        this.listener = listener
    }

    interface OnFoodClickListener {
        fun onFoodClicked(meal: Meal)
    }
}