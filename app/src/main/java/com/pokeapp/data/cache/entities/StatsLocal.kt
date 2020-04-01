package com.pokeapp.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Filipi Andrade on 31/03/2020
 */

@Entity(tableName = "stats")
class StatsLocal(@PrimaryKey
            var name: String = "",
                 var base_state: Int = 0)