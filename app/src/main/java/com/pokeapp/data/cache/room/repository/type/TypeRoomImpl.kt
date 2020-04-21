package com.pokeapp.data.cache.room.repository.type

import com.pokeapp.PokeApplication
import com.pokeapp.data.cache.entities.TypeLocal

/**
 * Created by Filipi Andrade on 05/04/2020
 */
class TypeRoomImpl : TypeRoom {

    override fun insert(typeLocal: TypeLocal): Boolean {
        if (PokeApplication.database == null) return false

        PokeApplication.database?.apply {
            pokemonDao().insertType(typeLocal)
        }
        Result
        return true
    }

    override fun getAll(): MutableList<TypeLocal>? {
        if (PokeApplication.database == null) return mutableListOf()

        val result = PokeApplication.database?.pokemonDao()?.getTypes()
        Result
        return result

    }
}