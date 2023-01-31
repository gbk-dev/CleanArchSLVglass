package com.example.cleanarchslvglass.presentation.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cleanarchslvglass.presentation.fragments.ARG_BACKGROUND
import com.example.cleanarchslvglass.presentation.fragments.ARG_TEXT
import com.example.cleanarchslvglass.presentation.fragments.StageFragment

class StageOfProductionAdapter(
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {

    private var listText = emptyList<String>()
    private var listBack = emptyList<String>()

    override fun getItemCount(): Int {
        return listText.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = StageFragment()
        val bundle = Bundle()
        bundle.putString(ARG_TEXT, listText[position])
        bundle.putString(ARG_BACKGROUND, listBack[position])
        fragment.arguments = bundle
        return fragment
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setText(newListText: List<String>, newListBack: List<String>){
        this.listText = newListText
        this.listBack = newListBack
        notifyDataSetChanged()
    }

}