package com.pokeapp.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class TypeLocal(
    @PrimaryKey
    var name: String = ""
)