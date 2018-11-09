package com.example.neven.foodorderapp.food

import com.example.neven.foodorderapp.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FoodModule {

    @Provides
    @ActivityScope
    fun provideFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository {
        return foodRepositoryImpl
    }

    @Provides
    @ActivityScope
    fun provideFoodViewModelFactory(repositoryImpl: FoodRepositoryImpl, compositeDisposable: CompositeDisposable): FoodViewmodelFactory {
        return FoodViewmodelFactory(repositoryImpl, compositeDisposable)
    }


}