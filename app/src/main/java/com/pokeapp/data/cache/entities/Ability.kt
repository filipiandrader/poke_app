package com.pokeapp.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ability")
data class Ability(@PrimaryKey
                   var name: String = "")