package com.dexapp.base_presentation.model.pokemon

import android.os.Parcelable
import com.dexapp.base_presentation.model.stats.StatsBinding
import com.dexapp.base_presentation.model.type.TypeBinding
import com.dexapp.base_presentation.model.ability.AbilityBinding
import com.dexapp.base_presentation.model.evolution.EvolutionBinding
import com.dexapp.base_presentation.model.move.MoveBinding
import kotlinx.android.parcel.Parcelize

/**
 * Created by Filipi Andrade on 29/03/2020
 */

@Parcelize
class PokemonInfoBinding(
    var id: Int,
    var name: String,
    var photo: String,
    var photoShiny: String,
    var generationName: String,
    var description: String,
    var baseExperience: Int,
    var height: Int,
    var weight: Int,
    var types: List<TypeBinding>,
    var abilities: List<AbilityBinding>,
    var moves: List<MoveBinding>,
    var stats: List<StatsBinding>,
    var evolution: List<EvolutionBinding>,
    var liked: Boolean = false
) : Parcelable