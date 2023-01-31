package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.repository.UserAuthRepository

class SetUserUseCase(private val userAuthRepository: UserAuthRepository) {

    suspend fun createUser(user: User, password: String): String{
        return userAuthRepository.createUser(user, password)
    }

}