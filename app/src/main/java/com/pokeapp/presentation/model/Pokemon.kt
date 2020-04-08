package com.pokeapp.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
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
                   var favourite: Boolean = false) : BaseObservable(), Serializable {

    var _id
        @Bindable
        get() = id
        set(value) {
            id = value
            notifyChange()
        }

    var _name
        @Bindable
        get() = name
        set(value) {
            name = value
            notifyChange()
        }

    var _photo
        @Bindable
        get() = photo
        set(value) {
            photo = value
            notifyChange()
        }

    var _photo_shiny
        @Bindable
        get() = photo_shiny
        set(value) {
            photo_shiny = value
            notifyChange()
        }

    var _generation
        @Bindable
        get() = generation
        set(value) {
            generation = value
            notifyChange()
        }

    var _base_experience
        @Bindable
        get() = base_experience
        set(value) {
            base_experience = value
            notifyChange()
        }

    var _height
        @Bindable
        get() = height
        set(value) {
            height = value
            notifyChange()
        }

    var _weight
        @Bindable
        get() = weight
        set(value) {
            weight = value
            notifyChange()
        }

    var _types
        @Bindable
        get() = types
        set(value) {
            types = value
            notifyChange()
        }

    var _abilities
        @Bindable
        get() = abilities
        set(value) {
            abilities = value
            notifyChange()
        }

    var _moves
        @Bindable
        get() = moves
        set(value) {
            moves = value
            notifyChange()
        }

    var _stats
        @Bindable
        get() = stats
        set(value) {
            stats = value
            notifyChange()
        }

    var _evolves
        @Bindable
        get() = evolves
        set(value) {
            evolves = value
            notifyChange()
        }

    var _favourite
        @Bindable
        get() = favourite
        set(value) {
            favourite = value
            notifyChange()
        }
}