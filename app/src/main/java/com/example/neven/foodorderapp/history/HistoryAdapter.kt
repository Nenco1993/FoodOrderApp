package com.example.neven.foodorderapp.history

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.neven.foodorderapp.R
import com.example.neven.foodorderapp.data.OrderDetails
import kotlinx.android.synthetic.main.list_item_food.view.*
import kotlinx.android.synthetic.main.list_item_history.view.*
import org.jetbrains.anko.layoutInflater

class HistoryAdapter() : RecyclerView.Adapter<HistoryViewHolder>() {

    val listHistory = mutableListOf<OrderDetails>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HistoryViewHolder {
        val view = p0.context.layoutInflater.inflate(R.layout.list_item_history, p0, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val singleOrder = listHistory[position]
        val fullName = singleOrder.fullName ?: "noname"
        val address = singleOrder.address ?: "no address"
        holder.view.tvHistoryFullName.text = fullName
        holder.view.tvHistoryAddress.text = address
    }

    fun addData(listHistory: List<OrderDetails>?) {
        this.listHistory.clear()
        listHistory?.let {
            this.listHistory.addAll(it)
            notifyItemRangeInserted(0, this.listHistory.size)
        }
    }

    fun removeData() {
        this.listHistory.clear()
        notifyDataSetChanged()
    }
}