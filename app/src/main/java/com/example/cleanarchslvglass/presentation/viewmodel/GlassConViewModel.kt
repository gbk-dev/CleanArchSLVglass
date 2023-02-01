package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.models.GlassContainer
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.usecase.AddToBasketUseCase
import com.example.cleanarchslvglass.domain.usecase.CheckLanguageProductsUseCase
import com.example.cleanarchslvglass.domain.usecase.GetGlassContainerUseCase
import com.example.cleanarchslvglass.domain.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlassConViewModel @Inject constructor(
    private val getGlassContainerUseCase: GetGlassContainerUseCase,
    private val checkLanguageProductsUseCase: CheckLanguageProductsUseCase,
    private val addToBasketUseCase: AddToBasketUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _glassConList = MutableLiveData<List<GlassContainer>>()
    val glassConList : LiveData<List<GlassContainer>> by lazy {
        _glassConList
    }

    fun getGlassCon(){
        viewModelScope.launch(Dispatchers.IO) {
            getGlassContainerUseCase.getGlassCon().collect(){
                when {
                    it.isSuccess -> {
                        val getGlassConList = it.getOrNull()
                        _glassConList.postValue(getGlassConList!!)
                    }

                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun checkLanguage(language: String): String{
        return checkLanguageProductsUseCase.checkLanguage(language)
    }

    fun insert(basket: Basket){
        viewModelScope.launch(Dispatchers.IO) {
            addToBasketUseCase.insert(basket)
        }
    }

    private val _userList = MutableLiveData<User>()
    val userList : LiveData<User> by lazy {
        _userList
    }

    fun getUser(){
        viewModelScope.launch(Dispatchers.IO) {
            getUserDataUseCase.getUserData().collect(){
                when{
                    it.isSuccess -> {
                        val getUserList = it.getOrNull()
                        _userList.postValue(getUserList!!)
                    }

                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }
}