package com.example.cleanarchslvglass.domain.models

data class Basket(
    val id : Int,
    val article : String,
    val title : String,
    val capacity : String,
    val collarType : String,
    val count : Int,
    val img : String,
    val userId : String
)
