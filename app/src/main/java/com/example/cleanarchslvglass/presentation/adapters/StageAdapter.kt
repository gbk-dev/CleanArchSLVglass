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
import com.example.cleanarchslvglass.domain.models.Stage

class StageAdapter(private val context: Context) : RecyclerView.Adapter<StageAdapter.StageViewHolder>() {

    private var stageList = emptyList<Stage>()

    private lateinit var mListener : onStageClickListener

    interface onStageClickListener{
        fun onItemCLick(position: Int)
    }

    fun setOnItemClickListener(listener: onStageClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.products_item,
            parent,
            false)
        return StageViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: StageViewHolder, position: Int) {
        val currentItem = stageList[position]

        holder.title.text = currentItem.title

        Glide.with(context).load(currentItem.img).into(holder.img)
    }

    override fun getItemCount(): Int {
        return stageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setStageList(newStageList: List<Stage>){
        this.stageList = newStageList
        notifyDataSetChanged()
    }

    class StageViewHolder(itemView: View, listener: onStageClickListener): RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(R.id.subcategory_title)
        val img : ImageView = itemView.findViewById(R.id.subcategory_img)

        init {
            itemView.setOnClickListener {
                listener.onItemCLick(adapterPosition)
            }
        }
    }

}