package com.example.cleanarchslvglass.domain.repository

import com.example.cleanarchslvglass.domain.models.User

interface UserAuthRepository {

    suspend fun createUser(user: User, password: String): String

    suspend fun authUser(user: User, password: String): String

    fun checkUser(): Boolean
}