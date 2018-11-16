package com.example.neven.foodorderapp.food

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.neven.foodorderapp.R
import com.example.neven.foodorderapp.base.BaseActivity
import com.example.neven.foodorderapp.order.FoodOrderActivity
import com.jakewharton.rxbinding.view.RxView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_food_order.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class FoodActivity : BaseActivity(), FoodAdapter.OnFoodClickListener {

    @Inject
    lateinit var viewmodelFactoryFood: FoodViewmodelFactory

    lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewmodel = ViewModelProviders.of(this, viewmodelFactoryFood).get(FoodViewModel::class.java)

        viewmodel.getFoodLiveData()?.observe(this, Observer {
            observeViewmodel(it)
        })

        val manager = LinearLayoutManager(this)
        rvFood.layoutManager = manager
        rvFood.itemAnimator = DefaultItemAnimator()
        adapter = FoodAdapter()
    }

    override fun onFoodClicked(meal: Meal) {
        val intent = Intent(baseContext, FoodOrderActivity::class.java)
        intent.putExtra(FoodConstants.KEY_MEAL, meal)
        startActivity(intent)
    }

    fun observeViewmodel(listMeals: List<Meal>?) {
        if (listMeals != null) {
            showData(listMeals)
        } else {
            showErrorMessage(getString(R.string.error_message))
        }
    }

    fun showErrorMessage(errorMessage: String) {
        toast(errorMessage)
    }


    fun showData(listMeals: List<Meal>?) {
        rvFood.adapter = adapter
        adapter.setData(listMeals)
        adapter.setOnFoodClickListener(this)
    }
}
