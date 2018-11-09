package com.example.neven.foodorderapp.di.modules

import com.example.neven.foodorderapp.network.RestAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    companion object {
        val BASE_URL: String = "https://www.themealdb.com/api/json/v1/1/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit
    }

    @Provides
    @Singleton
    fun provideRestAPI(retrofit: Retrofit): RestAPI {
        return retrofit.create(RestAPI::class.java)
    }
}