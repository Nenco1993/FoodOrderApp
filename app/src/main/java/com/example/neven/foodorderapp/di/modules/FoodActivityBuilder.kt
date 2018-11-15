package com.example.neven.foodorderapp.di.modules

import com.example.neven.foodorderapp.di.scopes.ActivityScope
import com.example.neven.foodorderapp.food.FoodActivity
import com.example.neven.foodorderapp.food.FoodModule
import com.example.neven.foodorderapp.order.FoodOrderActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FoodActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FoodModule::class))
    abstract fun bindFoodActivity(): FoodActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FoodModule::class))
    abstract fun bindFoodOrderActivity(): FoodOrderActivity
}
