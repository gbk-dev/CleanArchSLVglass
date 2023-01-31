package com.example.cleanarchslvglass.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.domain.models.Category

class CategoryAdapter(private val context: Context) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList = emptyList<Category>()

    private lateinit var mListener : onCategoryClickListener

    interface onCategoryClickListener{
        fun onItemCLick(position: Int)
    }

    fun setOnItemClickListener(listener: onCategoryClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.categories_item,
            parent,
            false)

        return CategoryViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = categoryList[position]

        holder.description.text = currentItem.description

        Glide.with(context).load(currentItem.img).into(holder.img)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setCategoryList(categoryList: List<Category>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    class CategoryViewHolder(itemView : View, listener: onCategoryClickListener) : RecyclerView.ViewHolder(itemView){
        val description : TextView = itemView.findViewById(R.id.category_description)
        val img : ImageView = itemView.findViewById(R.id.category_img)

        init {
            itemView.setOnClickListener {
                listener.onItemCLick(adapterPosition)
            }
        }
    }

}