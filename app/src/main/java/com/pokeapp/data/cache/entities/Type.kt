package com.pokeapp.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class Type(@PrimaryKey
                var name: String = "")