package com.example.cleanarchslvglass.data.repository

import com.example.cleanarchslvglass.data.storage.UserStorage
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.repository.UserRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun getUserData(): Flow<Result<User>> {

        val userModel = userStorage.getUserData()
        val flowUser = callbackFlow<Result<User>> {
            userModel.collect {
                when {
                    it.isSuccess -> {
                        val userModelC = it.getOrNull()
                        val user = User(
                            firstName = userModelC?.firstName,
                            lastName = userModelC?.lastName,
                            email = userModelC?.email,
                            uid = userModelC?.uid
                        )
                        this@callbackFlow.trySendBlocking(Result.success(user))
                    }
                    it.isFailure -> {
                        this@callbackFlow.trySendBlocking(Result.failure(it.exceptionOrNull()!!))
                    }
                }
            }
            awaitClose {

            }
        }
        return flowUser
    }

    override fun logOut() {
        return userStorage.logOut()
    }

    override fun updateUser(user: Map<String, String>): String {
        return userStorage.updateUser(user = user)
    }

}