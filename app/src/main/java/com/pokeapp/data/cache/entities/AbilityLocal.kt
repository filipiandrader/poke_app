package com.pokeapp.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ability")
data class AbilityLocal(@PrimaryKey
                   var name: String = "")