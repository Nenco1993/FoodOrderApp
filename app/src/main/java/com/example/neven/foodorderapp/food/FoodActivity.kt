package com.example.neven.foodorderapp.food

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.neven.foodorderapp.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class FoodActivity : AppCompatActivity() {

    @Inject
    lateinit var viewmodelFactoryFood: FoodViewmodelFactory

    var adapter: FoodAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewmodel = ViewModelProviders.of(this, viewmodelFactoryFood).get(FoodViewModel::class.java)
        viewmodel.loadData()
        viewmodel.getFoodLiveData()?.observe(this, Observer<Food> {
            observeViewmodel(it)
        })

        val manager = LinearLayoutManager(this)
        rvFood.layoutManager = manager
        rvFood.itemAnimator = DefaultItemAnimator()
        adapter = FoodAdapter()

    }

    fun observeViewmodel(food: Food?) {
        if (food != null) {
            showData(food)
        } else {
            showErrorMessage(getString(R.string.error_message))
        }
    }

    fun showErrorMessage(errorMessage: String) {
        toast(errorMessage)
    }


    fun showData(food: Food?) {
        rvFood.adapter = adapter
        adapter?.setData(food)
    }
}
