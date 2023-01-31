package com.example.cleanarchslvglass.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.data.db.RoomDataBase
import com.example.cleanarchslvglass.data.repository.OrdersRepositoryImpl
import com.example.cleanarchslvglass.data.storage.SharedPrefOrdersStorage
import com.example.cleanarchslvglass.domain.models.Orders
import com.example.cleanarchslvglass.domain.usecase.AddOrdersUseCase
import com.example.cleanarchslvglass.domain.usecase.DeleteAllOrdersUseCase
import com.example.cleanarchslvglass.domain.usecase.GetOrdersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel(
    application : Application
) : AndroidViewModel(application) {

    private var ordersRepository: OrdersRepositoryImpl

    init {
        val ordersDao = RoomDataBase.getDatabase(application).ordersDao()
        ordersRepository = OrdersRepositoryImpl(ordersStorage = SharedPrefOrdersStorage(ordersDao))
    }

    private val getOrdersUseCase by lazy {
        GetOrdersUseCase(ordersRepository = ordersRepository)
    }

    private val addOrdersUseCase by lazy {
        AddOrdersUseCase(ordersRepository = ordersRepository)
    }

    private val deleteAllOrdersUseCase by lazy {
        DeleteAllOrdersUseCase(ordersRepository = ordersRepository)
    }

    private val _ordersList = MutableLiveData<List<Orders>>()
    val ordersList : LiveData<List<Orders>> by lazy {
        _ordersList
    }

    fun getAllOrders(){
        viewModelScope.launch(Dispatchers.IO) {
            getOrdersUseCase.getAllOrders().collect{
                _ordersList.postValue(it)
            }
        }
    }

    fun insertOrders(orders: Orders){
        viewModelScope.launch(Dispatchers.IO) {
            addOrdersUseCase.insertOrders(orders = orders)
        }
    }

    fun deleteAllOrders(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllOrdersUseCase.deleteAllOrders()
        }
    }

}