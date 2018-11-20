package com.example.neven.foodorderapp.history

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.neven.foodorderapp.R
import com.example.neven.foodorderapp.data.OrderDetails
import com.example.neven.foodorderapp.food.FoodViewmodelFactory
import com.jakewharton.rxbinding.widget.RxTextView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_food_order_history.*
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import android.support.v7.widget.DividerItemDecoration

class FoodOrderHistoryActivity : AppCompatActivity() {

    @Inject
    lateinit var viewmodelFactoryFood: FoodViewmodelFactory

    lateinit var viewmodel: FoodOrderHistoryViewModel

    val adapter = HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_order_history)
        viewmodel = ViewModelProviders.of(this, viewmodelFactoryFood).get(FoodOrderHistoryViewModel::class.java)

        val manager = LinearLayoutManager(baseContext)
        rvHistory.layoutManager = manager
        val dividerItemDecoration = DividerItemDecoration(rvHistory.context, manager.orientation)
        rvHistory.addItemDecoration(dividerItemDecoration)
        rvHistory.adapter = adapter
        viewmodel.liveDataHistory.observe(this, Observer {
            showHistory(it)
        })
        setOnSearchChangedListener()
    }

    private fun showHistory(listHistory: List<OrderDetails>?) {
        if (listHistory == null) {
            toast("ups, something went wrong :(")
        } else {
            adapter.addData(listHistory)
        }
    }

    private fun setOnSearchChangedListener() {
        RxTextView.textChanges(etSearch)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            adapter.removeData()
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
