package com.example.cleanarchslvglass.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "basket_table")
data class BasketModel(

    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "article") val article : String,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "capacity") val capacity : String,
    @ColumnInfo(name = "collarType") val collarType : String,
    @ColumnInfo(name = "count") val count : Int,
    @ColumnInfo(name = "img") val img : String,
    @ColumnInfo(name = "userId") val userId : String

) : Parcelable
