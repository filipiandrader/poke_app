package com.pokeapp.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.pokeapp.data_local.base.DataConverter

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Entity(tableName = "pokemon")
@TypeConverters(DataConverter::class)
class PokemonLocal(@PrimaryKey
                   var id: Int = 0,
                   var name: String = "",
                   var photo: String = "",
                   var photoShiny: String = "",
                   var generation: String = "",
                   var about: String = "",
                   var height: Int = -1,
                   var baseExperience: Int = -1,
                   var weight: Int = -1,
                   var types: MutableList<TypeLocal> = mutableListOf(),
                   var abilities: MutableList<AbilityLocal> = mutableListOf(),
                   var moves: MutableList<MoveLocal> = mutableListOf(),
                   var stats: MutableList<StatsLocal> = mutableListOf(),
                   var evolves: MutableList<SpeciesLocal> = mutableListOf(),
                   var liked: Boolean = false)