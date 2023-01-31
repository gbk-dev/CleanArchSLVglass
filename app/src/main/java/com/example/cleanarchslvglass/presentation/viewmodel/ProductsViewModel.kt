package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.data.repository.ProductsRepositoryImpl
import com.example.cleanarchslvglass.domain.models.Glass
import com.example.cleanarchslvglass.domain.models.GlassContainer
import com.example.cleanarchslvglass.domain.models.Mirror
import com.example.cleanarchslvglass.domain.usecase.CheckLanguageProductsUseCase
import com.example.cleanarchslvglass.domain.usecase.GetGlassContainerUseCase
import com.example.cleanarchslvglass.domain.usecase.GetGlassUseCase
import com.example.cleanarchslvglass.domain.usecase.GetMirrorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getGlassUseCase: GetGlassUseCase,
    private val getMirrorUseCase: GetMirrorUseCase,
    private val getGlassContainerUseCase: GetGlassContainerUseCase,
    private val checkLanguageProductsUseCase: CheckLanguageProductsUseCase
) : ViewModel() {

    private val _glassList = MutableLiveData<List<Glass>>()
    val glassList : LiveData<List<Glass>> by lazy {
        _glassList
    }

    private val _mirror = MutableLiveData<Mirror>()
    val mirror : LiveData<Mirror> by lazy {
        _mirror
    }

    private val _glassConList = MutableLiveData<List<GlassContainer>>()
    val glassConList : LiveData<List<GlassContainer>> by lazy {
        _glassConList
    }

    fun getGlass(){
        viewModelScope.launch(Dispatchers.IO) {
            getGlassUseCase.getGlass().collect{
                when {
                    it.isSuccess -> {
                        val getGlassList = it.getOrNull()
                        _glassList.postValue(getGlassList!!)
                    }

                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun getMirror(){
        viewModelScope.launch(Dispatchers.IO) {
            getMirrorUseCase.getMirror().collect{
                when {
                    it.isSuccess -> {
                        val getMirrorModel = it.getOrNull()
                        _mirror.postValue(getMirrorModel!!)
                    }

                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
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

}