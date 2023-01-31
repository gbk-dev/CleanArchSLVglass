package com.example.cleanarchslvglass.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.domain.models.Basket

class BasketAdapter (private val context: Context,
                     val callbackDelete: (Basket) -> Unit,
                     var callbackCount: (count: Int, id: Int) -> Unit)
    : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>(){

    private var basketList = emptyList<Basket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val currentItem = basketList[position]

        holder.basketArticle.text = currentItem.article
        holder.basketTitle.text = currentItem.title
        holder.basketDescription.text = currentItem.capacity
        holder.basketCount.text = currentItem.count.toString()

        Glide.with(context).load(currentItem.img).into(holder.basketImg)

        var count = currentItem.count

        holder.basketAdd.setOnClickListener {

            count += 1
            holder.basketCount.text = count.toString()
            callbackCount(count, currentItem.id)
        }

        holder.basketRemove.setOnClickListener {

            if (count > 1){
                count -= 1
                holder.basketCount.text = count.toString()
                callbackCount(count, currentItem.id)
            }

        }

        holder.basketDelete.setOnClickListener {
            val basket = Basket(currentItem.id, currentItem.article, currentItem.title, currentItem.capacity, currentItem.collarType, currentItem.count, currentItem.img, currentItem.userId)
            callbackDelete(basket)
        }
    }

    override fun getItemCount(): Int = basketList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItemToBasket(basket: List<Basket>){
        this.basketList = basket
        notifyDataSetChanged()
    }

    class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val basketArticle : TextView = itemView.findViewById(R.id.basketArticle)
        val basketTitle : TextView = itemView.findViewById(R.id.basketTitle)
        val basketDescription : TextView = itemView.findViewById(R.id.basketDescription)
        val basketImg : ImageView = itemView.findViewById(R.id.basketImg)

        val basketRemove : ImageButton = itemView.findViewById(R.id.basketRemove)
        val basketAdd : ImageButton = itemView.findViewById(R.id.basketAdd)
        val basketCount : TextView = itemView.findViewById(R.id.basketCount)
        val basketDelete : ImageButton = itemView.findViewById(R.id.basketDelete)
    }

}