package com.dexapp.data_local.model.move

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Filipi Andrade on 31/03/2020
 */

@Entity(tableName = "move")
data class MoveLocal(@PrimaryKey
                     var name: String = "")