package com.pokeapp.domain.favourite

import com.pokeapp.data.cache.room.repository.pokemon.PokemonRoom
import com.pokeapp.data.cache.room.repository.type.TypeRoom
import com.pokeapp.data.remote.repository.favourite.FavouriteRepository
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Type
import com.pokeapp.util.convertToPokemonList
import com.pokeapp.util.convertToTypeList
import com.pokeapp.util.convertToTypeList3
import com.pokeapp.util.convertToTypeLocal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class FavouriteDataSourceImpl(private val repository: FavouriteRepository,
                              private val pokemonRoom: PokemonRoom,
                              private val typeRoom: TypeRoom) : FavouriteDataSource {

    private var job: Job = Job()

    override fun getFavouritePokemon(onSuccess: (MutableList<Pokemon>) -> Unit, onFailure: () -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = pokemonRoom.getAll()

            if (response != null) {
                onSuccess(response.convertToPokemonList())
            } else {
                onFailure()
            }
        }
    }

    override fun getPokemonByGeneration(region: String, onSuccess: (MutableList<Pokemon>) -> Unit, onFailure: () -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = pokemonRoom.getPokemonByGeneration(region)

            if (response != null) {
                onSuccess(response.convertToPokemonList())
            } else {
                onFailure()
            }
        }
    }

    override fun getAllTypes(onSuccess: (MutableList<Type>) -> Unit, onFailure: () -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            if (typeRoom.getAll()!!.isEmpty()) {
                val response = repository.getAllTypes()

                if (response.throwable != null || response.data == null) {
                    response.throwable?.printStackTrace()
                    onFailure()
                    return@launch
                }

                response.data.results.forEach { typeRoom.insert(it.convertToTypeLocal()) }

                onSuccess(response.data.convertToTypeList3())
            } else {
                val response = typeRoom.getAll()

                onSuccess(response!!.convertToTypeList())
            }
        }
    }

    override fun getPokemonByType(type: String, onSuccess: (MutableList<Pokemon>) -> Unit, onFailure: () -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = pokemonRoom.getPokemonByType(type)

            if (response != null) {
                onSuccess(response.convertToPokemonList())
            } else {
                onFailure()
            }
        }
    }

    override fun cancelJob() {
        job.cancel()
    }
}