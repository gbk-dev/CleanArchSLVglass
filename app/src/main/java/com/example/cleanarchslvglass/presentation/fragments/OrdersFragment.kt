package com.example.cleanarchslvglass.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.FragmentOrdersBinding
import com.example.cleanarchslvglass.presentation.adapters.OrdersAdapter
import com.example.cleanarchslvglass.presentation.viewmodel.OrdersViewModel

class OrdersFragment : Fragment() {

    private val viewModel : OrdersViewModel by viewModels()
    private var _binding : FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(layoutInflater)
        binding.constOrders.visibility = View.VISIBLE
        val view = binding.root

        val toolbar = binding.ordersToolbar
        toolbar.title = resources.getString(R.string.menuTitle1)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)

        toolbar.setNavigationOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameOrders, SettingsFragment()).commit()
            binding.constOrders.visibility = View.INVISIBLE
        }

        recyclerView = binding.ordersRecycler
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        val adapter = OrdersAdapter(view.context)
        recyclerView.adapter = adapter

        viewModel.getAllOrders()
        viewModel.ordersList.observe(viewLifecycleOwner){
            adapter.setOrder(it)
        }

        return view
    }

    override fun onResume() {
        binding.ordersFloatingButton.setOnClickListener {
            viewModel.deleteAllOrders()
            val adapter = OrdersAdapter(binding.root.context)
            recyclerView.adapter = adapter
        }
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.ordersList.removeObservers(viewLifecycleOwner)
        _binding = null
    }

}