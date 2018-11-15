package com.example.neven.foodorderapp.di.modules

import com.example.neven.foodorderapp.data.FoodRepository
import com.example.neven.foodorderapp.data.FoodRepositoryImpl
import com.example.neven.foodorderapp.di.scopes.ActivityScope
import com.example.neven.foodorderapp.food.FoodViewmodelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository {
        return foodRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideFoodViewModelFactory(repositoryImpl: FoodRepositoryImpl, compositeDisposable: CompositeDisposable): FoodViewmodelFactory {
        return FoodViewmodelFactory(repositoryImpl, compositeDisposable)
    }

}