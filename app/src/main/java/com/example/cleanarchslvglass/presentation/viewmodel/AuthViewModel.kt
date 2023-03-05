package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.domain.models.Resource
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.usecase.AuthUserUseCase
import com.example.cleanarchslvglass.domain.usecase.CheckUserUseCase
import com.example.cleanarchslvglass.domain.usecase.SetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val setUserUseCase: SetUserUseCase,
    private val authUserUseCase: AuthUserUseCase,
    private val checkUserUseCase: CheckUserUseCase
) : ViewModel() {


    private val _signUpState = MutableLiveData<Resource<Boolean>>()
    val signUpState : LiveData<Resource<Boolean>> by lazy {
        _signUpState
    }

    private val _signInState = MutableLiveData<Resource<Boolean>>()
    val signInState : LiveData<Resource<Boolean>> by lazy {
        _signInState
    }

    fun signUp(user: User, password: String){
        viewModelScope.launch {
            setUserUseCase.createUser(user = user, password = password).collect{
                _signUpState.postValue(it)
            }
        }
    }

    fun signIn(user: User, password: String){
        viewModelScope.launch {
            authUserUseCase.authUser(user = user, password = password).collect{
                _signInState.postValue(it)
            }
        }
    }

    fun checkUser(): Boolean{
        return checkUserUseCase.checkUser()
    }

}