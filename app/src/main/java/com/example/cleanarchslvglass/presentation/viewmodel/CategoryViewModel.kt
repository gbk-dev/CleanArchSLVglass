package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.data.repository.CategoryRepositoryImpl
import com.example.cleanarchslvglass.domain.models.Category
import com.example.cleanarchslvglass.domain.usecase.CheckLanguageCategoryUseCase
import com.example.cleanarchslvglass.domain.usecase.GetCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val categoryRepository by lazy {
        CategoryRepositoryImpl()
    }

    private val getCategoryUseCase by lazy {
        GetCategoryUseCase(categoryRepository = categoryRepository)
    }

    private val checkLanguageCategoryUseCase by lazy {
        CheckLanguageCategoryUseCase(categoryRepository = categoryRepository)
    }

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList : LiveData<List<Category>> by lazy {
        _categoryList
    }

    fun checkLanguage(language: String): String{
        return checkLanguageCategoryUseCase.checkLanguage(language)
    }

    /*fun getCategory() {
        viewModelScope.launch {
            getCategoryUseCase.getCategory().collect {
                when{
                    it.isSuccess -> {
                        val getUserList = it.getOrNull()
                        _categoryList.value = getUserList!!
                    }
                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }*/

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoryUseCase.getCategory().collect {
                when{
                    it.isSuccess -> {
                        val getUserList = it.getOrNull()
                        _categoryList.postValue(getUserList!!)
                    }
                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }
}