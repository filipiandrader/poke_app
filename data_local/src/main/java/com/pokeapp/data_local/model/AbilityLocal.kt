package com.pokeapp.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ability")
data class AbilityLocal(@PrimaryKey
                        var name: String = "")