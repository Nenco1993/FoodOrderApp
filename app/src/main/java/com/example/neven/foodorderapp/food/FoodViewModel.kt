package com.example.neven.foodorderapp.food


import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.neven.foodorderapp.data.FoodRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FoodViewModel @Inject constructor(val repo: FoodRepositoryImpl, val compositeDisposable: CompositeDisposable) : ViewModel() {

    var liveDataMeals: MutableLiveData<List<Meal>>? = MutableLiveData()

    init {
        loadData()
    }

    fun getFoodLiveData(): MutableLiveData<List<Meal>>? {
        return liveDataMeals
    }

    @SuppressLint("CheckResult")
    fun loadData() {
        val disposable = repo.getFood()
                .subscribeOn(Schedulers.io())
                .map { it.meals }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            liveDataMeals?.value = it
                        },
                        {
                            it.printStackTrace()
                            liveDataMeals?.value = null
                        }
                )
        compositeDisposable.add(disposable)

    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}