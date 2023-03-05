package com.example.cleanarchslvglass.data.repository

import com.example.cleanarchslvglass.domain.models.Glass
import com.example.cleanarchslvglass.domain.models.GlassContainer
import com.example.cleanarchslvglass.domain.models.Mirror
import com.example.cleanarchslvglass.domain.repository.ProductsRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class ProductsRepositoryImpl : ProductsRepository {

    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var languageProducts = ""
    private lateinit var listenerGlass: ValueEventListener
    private lateinit var listenerMirror: ValueEventListener
    private lateinit var listenerGlassCon: ValueEventListener

    override fun getGlass() = callbackFlow<Result<List<Glass>>> {

        withContext(Dispatchers.IO) {

            listenerGlass = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val glass = snapshot.children.map { ds ->
                        ds.getValue(Glass::class.java)
                    }
                    this@callbackFlow.trySendBlocking(Result.success(glass.filterNotNull()))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }
            }

            if (checkLanguage(languageProducts) == "ru") {
                db.reference
                    .child("Glass")
                    .addValueEventListener(listenerGlass)

            } else {
                db.reference
                    .child("GlassEn")
                    .addValueEventListener(listenerGlass)
            }

        }

        awaitClose {
            db.reference
                .child("Glass")
                .removeEventListener(listenerGlass)

            db.reference
                .child("GlassEn")
                .addValueEventListener(listenerGlass)
        }
    }

    override fun getMirror()= callbackFlow<Result<Mirror>> {

        withContext(Dispatchers.IO) {

            listenerMirror = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val mirror = snapshot.getValue(Mirror::class.java)
                    this@callbackFlow.trySendBlocking(Result.success(mirror!!))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }
            }

            if (checkLanguage(languageProducts) == "ru") {
                db.reference
                    .child("Mirror")
                    .addValueEventListener(listenerMirror)

            } else {
                db.reference
                    .child("MirrorEn")
                    .addValueEventListener(listenerMirror)
            }

        }

        awaitClose {
            db.reference
                .child("Mirror")
                .removeEventListener(listenerMirror)

            db.reference
                .child("MirrorEn")
                .addValueEventListener(listenerMirror)
        }
    }

    override fun getGlassCon()= callbackFlow<Result<List<GlassContainer>>> {

        withContext(Dispatchers.IO) {

            listenerGlassCon = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val glassCon = snapshot.children.map { ds ->
                        ds.getValue(GlassContainer::class.java)
                    }
                    this@callbackFlow.trySendBlocking(Result.success(glassCon.filterNotNull()))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }
            }

            if (checkLanguage(languageProducts) == "ru") {
                db.reference
                    .child("GlassContainer")
                    .addValueEventListener(listenerGlassCon)
            } else {
                db.reference
                    .child("GlassContainerEn")
                    .addValueEventListener(listenerGlassCon)
            }

        }

        awaitClose {
            db.reference
                .child("GlassContainer")
                .removeEventListener(listenerGlassCon)

            db.reference
                .child("GlassContainerEn")
                .addValueEventListener(listenerGlassCon)
        }
    }

    override fun checkLanguage(language: String): String {
        languageProducts = language
        return language
    }
}