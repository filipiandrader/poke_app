package com.dexapp.data_local.model.ability

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ability")
data class AbilityLocal(@PrimaryKey
                        var name: String = "")