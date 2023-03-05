package com.example.cleanarchslvglass.data.repository

import com.example.cleanarchslvglass.domain.models.Resource
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.repository.UserAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class UserAuthRepositoryImpl : UserAuthRepository {

    private val dbAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()

    override suspend fun createUser(user: User, password: String): Flow<Resource<Boolean>> = callbackFlow {

        this@callbackFlow.trySendBlocking(Resource.Loading)

        try {

            dbAuth.createUserWithEmailAndPassword(user.email.toString(), password).addOnCompleteListener { createUser ->
                if (createUser.isSuccessful) {
                    val userId = dbAuth.currentUser?.uid
                    val userDb = User(
                        firstName = user.firstName,
                        lastName = user.lastName,
                        email = user.email,
                        uid = userId
                    )
                    db.reference.child("Users/$userId").setValue(userDb).addOnCompleteListener {
                        if (it.isSuccessful){
                            this@callbackFlow.trySendBlocking(Resource.Success(true))
                        }
                    }

                } else {
                    this@callbackFlow.trySendBlocking(Resource.Failure("Failed to sign up"))
                }
            }.await()

        } catch (e: Exception){
            this@callbackFlow.trySendBlocking(Resource.Failure(e.localizedMessage?: "An unexpected error"))
        }

        awaitClose {

        }
    }

    override suspend fun authUser(user: User, password: String): Flow<Resource<Boolean>> = callbackFlow {

        this@callbackFlow.trySendBlocking(Resource.Loading)

        try {

            dbAuth.signInWithEmailAndPassword(user.email.toString(), password).addOnCompleteListener {
                if (it.isSuccessful) {
                    this@callbackFlow.trySendBlocking(Resource.Success(true))
                } else {
                    this@callbackFlow.trySendBlocking(Resource.Failure("Failed to sign in"))
                }
            }.await()

        } catch (e: Exception) {
            this@callbackFlow.trySendBlocking(Resource.Failure(e.localizedMessage?: "An unexpected error"))
        }

        awaitClose {

        }
    }

    override fun checkUser(): Boolean {
        return dbAuth.currentUser != null
    }
}