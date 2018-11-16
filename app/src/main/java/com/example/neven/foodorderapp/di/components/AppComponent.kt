package com.example.neven.foodorderapp.di.components

import com.example.neven.foodorderapp.FoodOrderApplication
import com.example.neven.foodorderapp.di.modules.AppModule
import com.example.neven.foodorderapp.di.modules.FoodActivityBuilder
import com.example.neven.foodorderapp.di.modules.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, NetModule::class, FoodActivityBuilder::class))
interface AppComponent {

    @Component.Builder
    interface AppComponentBuilder {
        @BindsInstance
        fun setApplication(app: FoodOrderApplication): AppComponentBuilder
        fun build(): AppComponent
    }

    fun inject(activity: FoodOrderApplication)
}