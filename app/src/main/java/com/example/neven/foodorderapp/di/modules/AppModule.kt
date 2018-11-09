package com.example.neven.foodorderapp.di.modules

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
}