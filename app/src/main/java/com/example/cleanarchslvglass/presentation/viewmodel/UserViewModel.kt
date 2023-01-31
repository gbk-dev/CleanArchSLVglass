package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.data.repository.UserRepositoryImpl
import com.example.cleanarchslvglass.data.storage.SharedPrefUserStorage
import com.example.cleanarchslvglass.data.storage.UserStorage
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.usecase.GetUserDataUseCase
import com.example.cleanarchslvglass.domain.usecase.LogOutUseCase
import com.example.cleanarchslvglass.domain.usecase.UpdateUserDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val userRepository by lazy {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage())
    }

    private val getUserDataUseCase by lazy {
        GetUserDataUseCase(userRepository = userRepository)
    }

    private val logOutUseCase by lazy {
        LogOutUseCase(userRepository = userRepository)
    }

    private val updateUserDataUseCase by lazy {
        UpdateUserDataUseCase(userRepository = userRepository)
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

    fun logOut() : Unit{
        return logOutUseCase.logOut()
    }

    fun updateUser(user: Map<String, String>): String{
        return updateUserDataUseCase.updateUser(user)
    }

}