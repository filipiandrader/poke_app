package com.pokeapp.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 * Created by Filipi Andrade on 29/03/2020
 */
data class Pokemon(var name: String = "",
                   var photo: String = "",
                   var photo_shiny: String = "") : BaseObservable() {

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
}