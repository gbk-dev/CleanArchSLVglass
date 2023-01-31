package com.example.cleanarchslvglass.domain.models

data class Orders(
    val id : Int,
    val article : String,
    val title : String,
    val capacity : String,
    val collarType : String,
    val count : Int,
    val img : String,
    val userId : String
)
