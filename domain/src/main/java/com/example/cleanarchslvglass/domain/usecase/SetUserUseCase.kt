package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Resource
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.Flow

class SetUserUseCase(private val userAuthRepository: UserAuthRepository) {

    suspend fun createUser(user: User, password: String): Flow<Resource<Boolean>> {
        return userAuthRepository.createUser(user, password)
    }

}