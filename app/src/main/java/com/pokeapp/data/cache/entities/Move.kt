package com.pokeapp.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Filipi Andrade on 31/03/2020
 */

@Entity(tableName = "move")
data class Move(@PrimaryKey
                var name: String = "")