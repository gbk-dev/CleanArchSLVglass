package com.example.cleanarchslvglass.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BasketInstance {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("https://api.telegram.org/bot5429075876:AAGbHt9wEBA6W7Xhx1bK0JMPbSxRvRexfSI/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val api: BasketService by lazy {
        retrofit.create(BasketService::class.java)
    }

}