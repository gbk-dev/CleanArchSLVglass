package com.example.cleanarchslvglass.data.service

import com.example.cleanarchslvglass.data.models.BasketModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BasketService {

    @POST("sendMessage?chat_id=728083988&")
    @FormUrlEncoded
    suspend fun sendReq(@Field("text") message: String) : Response<BasketModel>

}