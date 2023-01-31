package com.example.cleanarchslvglass.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.domain.models.Orders

class OrdersAdapter(private val context: Context) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    private var ordersList = emptyList<Orders>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.orders_item, parent, false)
        return OrdersViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val currentItem = ordersList[position]

        holder.ordersArticle.text = currentItem.article
        holder.ordersTitle.text = currentItem.title
        holder.ordersDescription.text = currentItem.capacity
        holder.ordersCount.text = currentItem.count.toString()

        Glide.with(context).load(currentItem.img).into(holder.ordersImg)
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setOrder(orderList : List<Orders>){
        this.ordersList = orderList
        notifyDataSetChanged()
    }

    class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ordersArticle : TextView = itemView.findViewById(R.id.ordersArticle)
        val ordersTitle : TextView = itemView.findViewById(R.id.ordersTitle)
        val ordersDescription : TextView = itemView.findViewById(R.id.ordersDescription)
        val ordersImg : ImageView = itemView.findViewById(R.id.ordersImg)
        val ordersCount : TextView = itemView.findViewById(R.id.ordersCount)
    }

}