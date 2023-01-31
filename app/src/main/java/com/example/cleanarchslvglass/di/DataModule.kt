package com.example.cleanarchslvglass.di

import android.app.Application
import android.content.Context
import com.example.cleanarchslvglass.data.db.RoomDataBase
import com.example.cleanarchslvglass.data.repository.*
import com.example.cleanarchslvglass.data.storage.*
import com.example.cleanarchslvglass.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideUserStorage(): UserStorage{
        return SharedPrefUserStorage()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userStorage: UserStorage): UserRepository{
        return UserRepositoryImpl(userStorage = userStorage)
    }

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): RoomDataBase{
        return RoomDataBase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideBasketDao(roomDataBase: RoomDataBase): BasketDao{
        return roomDataBase.basketDao()
    }

    @Provides
    @Singleton
    fun provideBasketStorage(basketDao: BasketDao): BasketStorage{
        return SharedPrefBasketStorage(basketDao = basketDao)
    }

    @Provides
    @Singleton
    fun provideBasketRepository(basketStorage: BasketStorage): BasketRepository{
        return BasketRepositoryImpl(basketStorage = basketStorage)
    }

    @Provides
    @Singleton
    fun provideUserAuthRepository(): UserAuthRepository{
        return UserAuthRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(): CategoryRepository{
        return CategoryRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideOrdersDao(roomDataBase: RoomDataBase): OrdersDao{
        return roomDataBase.ordersDao()
    }

    @Provides
    @Singleton
    fun provideOrdersStorage(ordersDao: OrdersDao): OrdersStorage{
        return SharedPrefOrdersStorage(ordersDao = ordersDao)
    }

    @Provides
    @Singleton
    fun provideOrdersRepository(ordersStorage: OrdersStorage): OrdersRepository{
        return OrdersRepositoryImpl(ordersStorage = ordersStorage)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(): ProductsRepository{
        return ProductsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSettingsDao(roomDataBase: RoomDataBase): SettingsDao{
        return roomDataBase.settingsDao()
    }

    @Provides
    @Singleton
    fun provideSettingsStorage(settingsDao: SettingsDao): SettingsStorage{
        return SharedPrefSettingsStorage(settingsDao = settingsDao)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(settingsStorage: SettingsStorage): SettingsRepository{
        return SettingsRepositoryImpl(settingsStorage = settingsStorage)
    }

    @Provides
    @Singleton
    fun provideStageRepository(): StageRepository{
        return StageRepositoryImpl()
    }

}