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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase
) : ViewModel() {

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