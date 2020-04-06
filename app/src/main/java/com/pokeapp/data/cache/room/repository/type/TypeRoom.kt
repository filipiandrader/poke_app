package com.pokeapp.data.cache.room.repository.type

import com.pokeapp.data.cache.entities.TypeLocal

/**
 * Created by Filipi Andrade on 05/04/2020
 */
interface TypeRoom {

    fun insert(typeLocal: TypeLocal): Boolean

    fun getAll(): MutableList<TypeLocal>?
}