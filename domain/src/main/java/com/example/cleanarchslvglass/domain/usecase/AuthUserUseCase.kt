package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.repository.UserAuthRepository

class AuthUserUseCase(private val userAuthRepository: UserAuthRepository) {

    suspend fun authUser(user: User, password: String): String{
        return userAuthRepository.authUser(user, password)
    }

}