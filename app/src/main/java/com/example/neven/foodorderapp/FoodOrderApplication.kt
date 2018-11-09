package com.example.neven.foodorderapp

import android.app.Activity
import android.app.Application
import com.example.neven.foodorderapp.di.components.DaggerAppComponent


import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class FoodOrderApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}