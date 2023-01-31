package com.example.cleanarchslvglass.data.storage

import androidx.room.*
import com.example.cleanarchslvglass.data.models.BasketModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket_table ORDER BY id ASC")
    fun getAll(): Flow<List<BasketModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basket: BasketModel)

    @Delete
    suspend fun delete(basket: BasketModel)

    @Query("DELETE FROM basket_table")
    suspend fun deleteAll()

    @Query("UPDATE basket_table SET count=:count WHERE id=:id")
    suspend fun updateCount(count: Int, id: Int)

}