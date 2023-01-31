package com.example.cleanarchslvglass.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "settings_table")
data class SettingsModel(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "notificationState") val notificationState : Int
) : Parcelable
