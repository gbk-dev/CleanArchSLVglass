package com.example.cleanarchslvglass.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchslvglass.data.models.OrdersModel
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersDao {

    @Query("SELECT * FROM orders_table ORDER BY id ASC")
    fun getAllOrders(): Flow<List<OrdersModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrders(orders: OrdersModel)

    @Query("DELETE FROM orders_table")
    suspend fun deleteAllOrders()

}