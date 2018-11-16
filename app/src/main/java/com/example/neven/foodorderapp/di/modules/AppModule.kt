package com.example.neven.foodorderapp.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.neven.foodorderapp.FoodOrderApplication
import com.example.neven.foodorderapp.data.*
import com.example.neven.foodorderapp.food.FoodConstants
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
    fun provideFoodRepository(foodRepositoryImpl: FoodRepositoryImpl, dbManager: DatabaseManager): FoodRepository {
        return foodRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideFoodViewModelFactory(repositoryImpl: FoodRepositoryImpl, compositeDisposable: CompositeDisposable): FoodViewmodelFactory {
        return FoodViewmodelFactory(repositoryImpl, compositeDisposable)
    }

    @Provides
    @Singleton
    fun provideContext(application: FoodOrderApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): ReceiptsDatabase {
        return Room.databaseBuilder(
                context,
                ReceiptsDatabase::class.java,
                FoodConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideReceiptDao(db: ReceiptsDatabase): ReceiptDao {
        return db.receiptDao()
    }

    @Provides
    @Singleton
    fun provideDatabaseManager(receiptDao: ReceiptDao): DatabaseManager {
        return DatabaseManager(receiptDao)
    }
}