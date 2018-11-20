package com.example.neven.foodorderapp.order

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.neven.foodorderapp.R
import com.example.neven.foodorderapp.base.BaseActivity
import com.example.neven.foodorderapp.data.OrderDetails
import com.example.neven.foodorderapp.food.FoodConstants
import com.example.neven.foodorderapp.food.FoodViewmodelFactory
import com.example.neven.foodorderapp.food.Meal
import com.jakewharton.rxbinding.view.RxView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_food_order.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class FoodOrderActivity : BaseActivity() {

    @Inject
    lateinit var viewmodelFactoryFood: FoodViewmodelFactory

    lateinit var viewmodel: FoodOrderViewModel

    lateinit var meal: Meal

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_order)
        meal = intent?.getSerializableExtra(FoodConstants.KEY_MEAL) as Meal
        viewmodel = ViewModelProviders.of(this, viewmodelFactoryFood).get(FoodOrderViewModel::class.java)
        viewmodel.responseMessage.observe(this, Observer {
            showResponseMessage(it)
        })
        Glide.with(baseContext)
                .load(meal.strMealThumb)
                .into(ivFoodOrderPicture)
        setOnAddQuantityListener()
        setOnSubmitOrderClickListener()
    }

    private fun showResponseMessage(message: String?) {
        message?.let {
            toast(it)
            onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun setOnSubmitOrderClickListener() {
        RxView.clicks(bFoodOrder)
                .subscribe {
                    val orderDetails = OrderDetails(
                            etFoodOrderFullName.text.toString(),
                            etFullOrderAddress.text.toString(),
                            tvFoodOrderQuantity.text.toString(),
                            meal.strMeal
                    )
                    viewmodel.submitOrder(orderDetails)
                }
    }

    private fun setOnAddQuantityListener() {
        RxView.clicks(ivAddQuantity)
                .subscribe {
                    val currentQuantity = tvFoodOrderQuantity.text.toString()
                    var currentQuantityAsInt = currentQuantity.toInt()
                    currentQuantityAsInt++
                    tvFoodOrderQuantity.text = currentQuantityAsInt.toString()
                }
    }
}
