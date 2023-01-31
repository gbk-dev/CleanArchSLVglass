package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserDataUseCase(private val userRepository: UserRepository) {

    fun getUserData() : Flow<Result<User>> {
        return userRepository.getUserData()
    }

}