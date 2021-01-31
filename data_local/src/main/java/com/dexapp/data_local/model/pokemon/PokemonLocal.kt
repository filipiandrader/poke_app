package com.dexapp.data_local.model.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dexapp.data_local.base.DataConverter
import com.dexapp.data_local.model.evolution.EvolutionLocal
import com.dexapp.data_local.model.stats.StatsLocal
import com.dexapp.data_local.model.type.TypeLocal
import com.dexapp.data_local.model.ability.AbilityLocal
import com.dexapp.data_local.model.move.MoveLocal

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@Entity(tableName = "pokemon")
@TypeConverters(DataConverter::class)
class PokemonLocal(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var photo: String = "",
    var photoShiny: String = "",
    var generationName: String = "",
    var description: String = "",
    var height: Int = -1,
    var baseExperience: Int = -1,
    var weight: Int = -1,
    var types: MutableList<TypeLocal> = mutableListOf(),
    var abilities: MutableList<AbilityLocal> = mutableListOf(),
    var moves: MutableList<MoveLocal> = mutableListOf(),
    var stats: MutableList<StatsLocal> = mutableListOf(),
    var evolves: MutableList<EvolutionLocal> = mutableListOf(),
    var liked: Boolean = false
)