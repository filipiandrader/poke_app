package com.pokeapp.base_presentation.model

import android.os.Parcelable
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
    var evolves: List<SpeciesBinding>,
    var liked: Boolean = false
) : Parcelable