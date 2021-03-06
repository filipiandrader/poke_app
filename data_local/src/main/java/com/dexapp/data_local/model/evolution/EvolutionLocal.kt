package com.dexapp.data_local.model.evolution

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Filipi Andrade on 31/03/2020
 */
@Entity(tableName = "specie")
class EvolutionLocal(
    @PrimaryKey
    var name: String = "",
    var photo: String = ""
)