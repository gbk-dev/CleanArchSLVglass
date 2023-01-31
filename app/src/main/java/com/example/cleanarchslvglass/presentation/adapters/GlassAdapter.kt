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
import com.example.cleanarchslvglass.domain.models.Category
import com.example.cleanarchslvglass.domain.models.Glass

class GlassAdapter(private val context: Context) : RecyclerView.Adapter<GlassAdapter.GlassViewHolder>() {

    private var glassList = emptyList<Glass>()

    private lateinit var mListener : onGlassClickListener

    interface onGlassClickListener{
        fun onItemCLick(position: Int)
    }

    fun setOnItemClickListener(listener: onGlassClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.products_item,
            parent,
            false)
        return GlassViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: GlassViewHolder, position: Int) {
        val currentItem = glassList[position]

        holder.title.text = currentItem.title

        Glide.with(context).load(currentItem.img).into(holder.img)
    }

    override fun getItemCount(): Int {
        return glassList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setGlassList(glassList: List<Glass>){
        this.glassList = glassList
        notifyDataSetChanged()
    }

    class GlassViewHolder(itemView: View, listener: onGlassClickListener) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(R.id.subcategory_title)
        val img : ImageView = itemView.findViewById(R.id.subcategory_img)

        init {
            itemView.setOnClickListener {
                listener.onItemCLick(adapterPosition)
            }
        }
    }

}