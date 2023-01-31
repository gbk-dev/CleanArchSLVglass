package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cleanarchslvglass.data.repository.UserAuthRepositoryImpl
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.usecase.AuthUserUseCase
import com.example.cleanarchslvglass.domain.usecase.CheckUserUseCase
import com.example.cleanarchslvglass.domain.usecase.SetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val setUserUseCase: SetUserUseCase,
    private val authUserUseCase: AuthUserUseCase,
    private val checkUserUseCase: CheckUserUseCase
) : ViewModel() {

    suspend fun createUser(user: User, password: String): String{
        return setUserUseCase.createUser(user, password)
    }

    suspend fun authUser(user: User, password: String): String{
        return authUserUseCase.authUser(user, password)
    }

    fun checkUser(): Boolean{
        return checkUserUseCase.checkUser()
    }

}