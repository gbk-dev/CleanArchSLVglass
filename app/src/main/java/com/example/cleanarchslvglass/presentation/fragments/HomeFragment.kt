package com.example.cleanarchslvglass.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchslvglass.databinding.FragmentHomeBinding
import com.example.cleanarchslvglass.presentation.adapters.CategoryAdapter
import com.example.cleanarchslvglass.presentation.products.GlassActivity
import com.example.cleanarchslvglass.presentation.products.GlassContainerActivity
import com.example.cleanarchslvglass.presentation.products.MirrorActivity
import com.example.cleanarchslvglass.presentation.viewmodel.CategoryViewModel

class HomeFragment : Fragment() {

    private val viewModel : CategoryViewModel by viewModels()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        val recyclerView = binding.recyclerCategory
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        adapter = CategoryAdapter(view.context)
        recyclerView.adapter = adapter

        val getLanguage = resources.configuration.locale.language
        viewModel.checkLanguage(getLanguage)

        viewModel.getCategory()
        viewModel.categoryList.observe(viewLifecycleOwner) {
            adapter.setCategoryList(it)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.setOnItemClickListener(object : CategoryAdapter.onCategoryClickListener{
            override fun onItemCLick(position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(context, GlassActivity::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        val intent = Intent(context, GlassContainerActivity::class.java)
                        startActivity(intent)
                    }
                    2 -> {
                        val intent = Intent(context, MirrorActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.categoryList.removeObservers(viewLifecycleOwner)
        _binding = null
    }
}