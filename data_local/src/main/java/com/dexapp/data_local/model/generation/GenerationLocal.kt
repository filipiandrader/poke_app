package com.dexapp.data_local.model.generation

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

@Entity(tableName = "generation")
class GenerationLocal(
    @PrimaryKey
    var id: Int,
    var name: String,
    var region: String
)