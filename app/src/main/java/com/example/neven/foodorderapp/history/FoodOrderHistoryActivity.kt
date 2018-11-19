package com.example.neven.foodorderapp.history


import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.neven.foodorderapp.R
import com.example.neven.foodorderapp.food.FoodViewmodelFactory
import com.jakewharton.rxbinding.widget.RxTextView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_food_order_history.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class FoodOrderHistoryActivity : AppCompatActivity() {

    @Inject
    lateinit var viewmodelFactoryFood: FoodViewmodelFactory

    lateinit var viewmodel: FoodOrderHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_order_history)
        viewmodel = ViewModelProviders.of(this, viewmodelFactoryFood).get(FoodOrderHistoryViewModel::class.java)
        setOnSearchChangedListener()
    }

    private fun setOnSearchChangedListener() {
        RxTextView.textChanges(etSearch)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            val address = it.toString()
                            if (!address.isNullOrEmpty()) {
                                if (address.length > 2) {
                                    viewmodel.searchByAddress(address)
                                }

                            }
                        },
                        {
                            it.printStackTrace()
                        }
                )


    }
}
