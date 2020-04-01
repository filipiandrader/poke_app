package com.pokeapp.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.io.Serializable

/**
 * Created by Filipi Andrade on 01/04/2020
 */

class Region(var name: String = "",
             var main_generation: String = "",
             var locations: MutableList<Location> = mutableListOf(),
             var groups: MutableList<Groups> = mutableListOf()) : BaseObservable(), Serializable {

    var _name
        @Bindable
        get() = name
        set(value) {
            name = value
            notifyChange()
        }

    var _main_generation
        @Bindable
        get() = main_generation
        set(value) {
            main_generation = value
            notifyChange()
        }

    var _locations
        @Bindable
        get() = locations
        set(value) {
            locations = value
            notifyChange()
        }

    var _groups
        @Bindable
        get() = groups
        set(value) {
            groups = value
            notifyChange()
        }
}