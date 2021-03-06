package com.example.neven.foodorderapp.food

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.neven.foodorderapp.R
import com.example.neven.foodorderapp.base.BaseActivity
import com.example.neven.foodorderapp.history.FoodOrderHistoryActivity
import com.example.neven.foodorderapp.order.FoodOrderActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class FoodActivity : BaseActivity(), FoodAdapter.OnFoodClickListener {

    @Inject
    lateinit var viewmodelFactoryFood: FoodViewmodelFactory

    lateinit var adapter: FoodAdapter

    lateinit var viewmodel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this, viewmodelFactoryFood).get(FoodViewModel::class.java)
        viewmodel.getFoodLiveData()?.observe(this, Observer {
            observeViewmodel(it)
        })

        val manager = LinearLayoutManager(this)
        rvFood.layoutManager = manager
        rvFood.itemAnimator = DefaultItemAnimator()
        adapter = FoodAdapter()
        rvFood.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.history -> {
                val intent = Intent(baseContext, FoodOrderHistoryActivity::class.java)
                startActivity(intent)
            }

        }
        return true
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
        adapter.setData(listMeals)
        adapter.setOnFoodClickListener(this)
        adapter.notifyDataSetChanged()
    }
}
