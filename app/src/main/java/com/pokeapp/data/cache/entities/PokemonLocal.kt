package com.pokeapp.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.pokeapp.data.cache.DataConverter

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Entity(tableName = "pokemon")
@TypeConverters(DataConverter::class)
class PokemonLocal(@PrimaryKey
                   var id: Int = 0,
                   var name: String = "",
                   var photo: String = "",
                   var photo_shiny: String = "",
                   var height: Int = -1,
                   var base_experience: Int = -1,
                   var weight: Int = -1,
                   var types: MutableList<TypeLocal> = mutableListOf(),
                   var abilities: MutableList<AbilityLocal> = mutableListOf(),
                   var moves: MutableList<MoveLocal> = mutableListOf(),
                   var stats: MutableList<StatsLocal> = mutableListOf(),
                   var evolves: MutableList<SpeciesLocal> = mutableListOf(),
                   var favourite: Boolean = false)