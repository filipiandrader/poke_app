package com.pokeapp.base_presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Filipi Andrade on 29/03/2020
 */

@Parcelize
class PokemonBinding(
    var id: Int = 0,
    var name: String = "",
    var photo: String = "",
    var photo_shiny: String = "",
    var generation: String = "",
    var about: String = "",
    var base_experience: Int = -1,
    var height: Int = -1,
    var weight: Int = -1,
    var types: MutableList<TypeBinding> = mutableListOf(),
    var abilities: MutableList<AbilityBinding> = mutableListOf(),
    var moves: MutableList<MoveBinding> = mutableListOf(),
    var stats: MutableList<StatsBinding> = mutableListOf(),
    var evolves: MutableList<SpeciesBinding> = mutableListOf(),
    var favourite: Boolean = false
) : Parcelable