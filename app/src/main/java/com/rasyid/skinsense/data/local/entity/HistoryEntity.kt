package com.rasyid.skinsense.data.local.entity

import androidx.datastore.preferences.protobuf.Timestamp
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @field:ColumnInfo("id")
    @field:PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @field:ColumnInfo("timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @field:ColumnInfo("product")
    val product: String,

    @field:ColumnInfo("feature")
    val feature: String,

    @field:ColumnInfo("material")
    val material: String,
)