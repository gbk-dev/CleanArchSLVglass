package com.example.cleanarchslvglass.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchslvglass.databinding.FragmentBasketBinding
import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.models.Orders
import com.example.cleanarchslvglass.presentation.MainActivity
import com.example.cleanarchslvglass.presentation.adapters.BasketAdapter
import com.example.cleanarchslvglass.presentation.viewmodel.BasketViewModel
import com.example.cleanarchslvglass.presentation.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private var _binding : FragmentBasketBinding? = null
    private val binding get() = _binding!!
    private val viewModel : BasketViewModel by viewModels()
    private val viewModelOrders : OrdersViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(layoutInflater)
        val view = binding.root

        recyclerView = binding.basketRecycler
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        val adapter = BasketAdapter(view.context, viewModel::delete, viewModel::updateCount)

        viewModel.getAll()
        viewModel.basketList.observe(viewLifecycleOwner) {
            adapter.setItemToBasket(it)
            recyclerView.adapter = adapter
        }

        return view
    }

    override fun onResume() {
        viewModel.basketList.observe(viewLifecycleOwner){ listBasket ->
            if (listBasket.isNotEmpty()){
                binding.leftRequest.setOnClickListener {
                    var i = 0
                    lifecycleScope.launch(Dispatchers.IO) {
                        suspend {
                            while (i < listBasket.size){
                                viewModel.postMessage(
                                    ("Артикул - " + listBasket[i].article +
                                            "\n"
                                            + listBasket[i].title +
                                            "\n"
                                            + listBasket[i].capacity +
                                            "\n"
                                            + "Количество - " + listBasket[i].count +
                                            "\n"
                                            + "id пользователя - " + listBasket[i].userId +
                                            "\n"
                                            + listBasket[i].img)
                                        .replace("[", "")       // удаление символа "["
                                        .replace("]", "")       // удаление символа "]"
                                        .replace(",", "")
                                )
                                i++
                            }

                        }.invoke()

                        viewModel.deleteAll()
                        withContext(Dispatchers.Main){
                            val adapter = BasketAdapter(binding.root.context, viewModel::delete, viewModel::updateCount)
                            recyclerView.adapter = adapter

                            listBasket.onEach {
                                val orders = Orders(
                                    id = it.id,
                                    article = it.article,
                                    title = it.title,
                                    capacity = it.capacity,
                                    collarType = it.collarType,
                                    count = it.count,
                                    img = it.img,
                                    userId = it.userId
                                )

                                viewModelOrders.insertOrders(orders = orders)
                            }
                        }
                    }
                }
            }
        }
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.basketList.removeObservers(viewLifecycleOwner)
        _binding = null
    }

}