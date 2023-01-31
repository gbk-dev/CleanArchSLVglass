package com.example.cleanarchslvglass.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cleanarchslvglass.data.models.BasketModel
import com.example.cleanarchslvglass.data.models.OrdersModel
import com.example.cleanarchslvglass.data.models.SettingsModel
import com.example.cleanarchslvglass.data.storage.BasketDao
import com.example.cleanarchslvglass.data.storage.OrdersDao
import com.example.cleanarchslvglass.data.storage.SettingsDao

@Database(entities = [BasketModel :: class, OrdersModel::class, SettingsModel::class], version = 6)
abstract class RoomDataBase : RoomDatabase(){

    abstract fun basketDao() : BasketDao
    abstract fun ordersDao() : OrdersDao
    abstract fun settingsDao() : SettingsDao

    companion object{

        @Volatile
        private var INSTANCE : RoomDataBase? = null

        fun getDatabase(context: Context): RoomDataBase{

            val tempInstance = INSTANCE
            if (tempInstance != null){

                return tempInstance

            }
            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,
                    "app_database"

                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}