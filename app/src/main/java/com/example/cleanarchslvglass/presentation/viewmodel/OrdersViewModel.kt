package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.domain.models.Orders
import com.example.cleanarchslvglass.domain.usecase.AddOrdersUseCase
import com.example.cleanarchslvglass.domain.usecase.DeleteAllOrdersUseCase
import com.example.cleanarchslvglass.domain.usecase.GetOrdersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val addOrdersUseCase: AddOrdersUseCase,
    private val deleteAllOrdersUseCase: DeleteAllOrdersUseCase
) : ViewModel(){

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