package com.pokeapp.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class TypeLocal(@PrimaryKey
                     var name: String = "",
                     var id: Int = 0)