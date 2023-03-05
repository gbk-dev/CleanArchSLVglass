package com.example.cleanarchslvglass.data.repository

import com.example.cleanarchslvglass.domain.models.Category
import com.example.cleanarchslvglass.domain.repository.CategoryRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl : CategoryRepository{

    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var languageCategory = ""
    private lateinit var listener: ValueEventListener

    override fun getCategory() = callbackFlow<Result<List<Category>>>{

        withContext(Dispatchers.Default) {
            listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val category = snapshot.children.map { ds ->
                        ds.getValue(Category::class.java)
                    }
                    this@callbackFlow.trySendBlocking(Result.success(category.filterNotNull()))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }

            }

            if (checkLanguage(languageCategory) == "ru") {
                db.reference
                    .child("Category")
                    .addValueEventListener(listener)

            } else {
                db.reference
                    .child("CategoryEn")
                    .addValueEventListener(listener)
            }
        }

        awaitClose {
            db.reference
                .child("Category")
                .removeEventListener(listener)

            db.reference
                .child("CategoryEn")
                .removeEventListener(listener)
        }

    }

    override fun checkLanguage(language: String): String {
        languageCategory = language
        return language
    }

}