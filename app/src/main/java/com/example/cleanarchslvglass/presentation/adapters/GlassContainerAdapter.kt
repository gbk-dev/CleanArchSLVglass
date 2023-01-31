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
import com.example.cleanarchslvglass.domain.models.GlassContainer

class GlassContainerAdapter(private val context: Context) : RecyclerView.Adapter<GlassContainerAdapter.GlassContainerViewHolder>() {

    private var glassConList = emptyList<GlassContainer>()

    private lateinit var mListener : onGlassConClickListener

    interface onGlassConClickListener{
        fun onItemCLick(position: Int)
    }

    fun setOnItemClickListener(listener: onGlassConClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassContainerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.glasscon_item,
            parent,
            false)
        return GlassContainerViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: GlassContainerViewHolder, position: Int) {
        val currentItem = glassConList[position]

        holder.title.text = currentItem.title
        holder.capacity.text = currentItem.capacity

        Glide.with(context).load(currentItem.img).into(holder.img)
    }

    override fun getItemCount(): Int {
        return glassConList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setGlassConList(glassConList: List<GlassContainer>){
        this.glassConList = glassConList
        notifyDataSetChanged()
    }

    class GlassContainerViewHolder(itemView: View, listener: onGlassConClickListener) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(R.id.glassCon_title)
        var img : ImageView = itemView.findViewById(R.id.glassCon_img)
        var capacity : TextView = itemView.findViewById(R.id.glassCon_capacity)

        init {
            itemView.setOnClickListener {
                listener.onItemCLick(adapterPosition)
            }
        }
    }

}