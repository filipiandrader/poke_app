package com.pokeapp.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Filipi Andrade on 31/03/2020
 */
@Entity(tableName = "specie")
class SpeciesLocal(@PrimaryKey
                   var name: String = "",
                   var photo: String = "")