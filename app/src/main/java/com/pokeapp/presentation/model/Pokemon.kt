package com.pokeapp.presentation.model

import androidx.databinding.BaseObservable
import java.io.Serializable

/**
 * Created by Filipi Andrade on 29/03/2020
 */
data class Pokemon(var id: Int = 0,
                   var name: String = "",
                   var photo: String = "",
                   var photo_shiny: String = "",
                   var generation: String = "",
                   var base_experience: Int = -1,
                   var height: Int = -1,
                   var weight: Int = -1,
                   var types: MutableList<Type> = mutableListOf(),
                   var abilities: MutableList<Ability> = mutableListOf(),
                   var moves: MutableList<Move> = mutableListOf(),
                   var stats: MutableList<Stats> = mutableListOf(),
                   var evolves: MutableList<Species> = mutableListOf(),
                   var favourite: Boolean = false) : BaseObservable(), Serializable