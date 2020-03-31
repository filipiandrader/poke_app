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
                   var height: Int = -1,
                   var weight: Int = -1,
                   var types: MutableList<Type> = mutableListOf(),
                   var abilities: MutableList<Ability> = mutableListOf(),
                   var favorite: Boolean = false) : BaseObservable(), Serializable {

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

    var _favorite
        @Bindable
        get() = favorite
        set(value) {
            favorite = value
            notifyChange()
        }
}