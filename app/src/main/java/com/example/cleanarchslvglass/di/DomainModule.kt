package com.example.cleanarchslvglass.di

import com.example.cleanarchslvglass.domain.repository.*
import com.example.cleanarchslvglass.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetUserDataUseCase(userRepository: UserRepository): GetUserDataUseCase{
        return GetUserDataUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideLogOutUseCase(userRepository: UserRepository): LogOutUseCase{
        return LogOutUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideUpdateUserDataUseCase(userRepository: UserRepository): UpdateUserDataUseCase{
        return UpdateUserDataUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideGetBasketUseCase(basketRepository: BasketRepository): GetBasketUseCase{
        return GetBasketUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideAddToBasketUseCase(basketRepository: BasketRepository): AddToBasketUseCase {
        return AddToBasketUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideDeleteFromBasketUseCase(basketRepository: BasketRepository): DeleteFromBasketUseCase{
        return DeleteFromBasketUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideDeleteAllFromBasketUseCase(basketRepository: BasketRepository): DeleteAllFromBasketUseCase{
        return DeleteAllFromBasketUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideUpdateCountBasketUseCase(basketRepository: BasketRepository): UpdateCountBasketUseCase{
        return UpdateCountBasketUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun providePostMessageUseCase(basketRepository: BasketRepository): PostMessageUseCase{
        return PostMessageUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideSetUserUseCase(userAuthRepository: UserAuthRepository): SetUserUseCase{
        return SetUserUseCase(userAuthRepository = userAuthRepository)
    }

    @Provides
    fun provideAuthUserUseCase(userAuthRepository: UserAuthRepository): AuthUserUseCase{
        return AuthUserUseCase(userAuthRepository = userAuthRepository)
    }

    @Provides
    fun provideCheckUserUseCase(userAuthRepository: UserAuthRepository): CheckUserUseCase{
        return CheckUserUseCase(userAuthRepository = userAuthRepository)
    }

    @Provides
    fun provideGetCategoryUseCase(categoryRepository: CategoryRepository): GetCategoryUseCase{
        return GetCategoryUseCase(categoryRepository = categoryRepository)
    }

    @Provides
    fun provideCheckLanguageCategoryUseCase(categoryRepository: CategoryRepository): CheckLanguageCategoryUseCase{
        return CheckLanguageCategoryUseCase(categoryRepository = categoryRepository)
    }

    @Provides
    fun provideGetOrdersUseCase(ordersRepository: OrdersRepository): GetOrdersUseCase{
        return GetOrdersUseCase(ordersRepository = ordersRepository)
    }

    @Provides
    fun provideAddOrdersUseCase(ordersRepository: OrdersRepository): AddOrdersUseCase{
        return AddOrdersUseCase(ordersRepository = ordersRepository)
    }

    @Provides
    fun provideDeleteAllOrdersUseCase(ordersRepository: OrdersRepository): DeleteAllOrdersUseCase{
        return DeleteAllOrdersUseCase(ordersRepository = ordersRepository)
    }

    @Provides
    fun provideGetGlassUseCase(productsRepository: ProductsRepository): GetGlassUseCase{
        return GetGlassUseCase(productsRepository = productsRepository)
    }

    @Provides
    fun provideGetMirrorUseCase(productsRepository: ProductsRepository): GetMirrorUseCase{
        return GetMirrorUseCase(productsRepository = productsRepository)
    }

    @Provides
    fun provideGetGlassContainerUseCase(productsRepository: ProductsRepository): GetGlassContainerUseCase{
        return GetGlassContainerUseCase(productsRepository = productsRepository)
    }

    @Provides
    fun provideCheckLanguageProductsUseCase(productsRepository: ProductsRepository): CheckLanguageProductsUseCase{
        return CheckLanguageProductsUseCase(productsRepository = productsRepository)
    }

    @Provides
    fun provideGetNotificationStateUseCase(settingsRepository: SettingsRepository): GetNotificationStateUseCase{
        return GetNotificationStateUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideUpdateNotificationStateUseCase(settingsRepository: SettingsRepository): UpdateNotificationStateUseCase{
        return UpdateNotificationStateUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideSetSettingsUseCase(settingsRepository: SettingsRepository): SetSettingsUseCase{
        return SetSettingsUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideGetStageGlassUseCase(stageRepository: StageRepository): GetStageGlassUseCase{
        return GetStageGlassUseCase(stageRepository = stageRepository)
    }

    @Provides
    fun provideGetStageSodiumLiquidGlassUseCase(stageRepository: StageRepository): GetStageSodiumLiquidGlassUseCase{
        return GetStageSodiumLiquidGlassUseCase(stageRepository = stageRepository)
    }

    @Provides
    fun provideGetStageSodiumSilicateUseCase(stageRepository: StageRepository): GetStageSodiumSilicateUseCase{
        return GetStageSodiumSilicateUseCase(stageRepository = stageRepository)
    }

    @Provides
    fun provideGetStageUseCase(stageRepository: StageRepository): GetStageUseCase{
        return GetStageUseCase(stageRepository = stageRepository)
    }

    @Provides
    fun provideCheckLanguageStageUseCase(stageRepository: StageRepository): CheckLanguageStageUseCase{
        return CheckLanguageStageUseCase(stageRepository = stageRepository)
    }

}