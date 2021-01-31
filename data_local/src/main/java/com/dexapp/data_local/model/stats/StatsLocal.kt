package com.dexapp.data_local.model.stats

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Filipi Andrade on 31/03/2020
 */

@Entity(tableName = "stats")
class StatsLocal(@PrimaryKey
                 var name: String = "",
                 var baseState: Int = 0)