package com.pokeapp.domain.favourite

import com.pokeapp.data.cache.room.repository.PokemonRoom
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.util.convertToPokemonList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class FavouriteDataSourceImpl(private val pokemonRoom: PokemonRoom) : FavouriteDataSource {

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

    override fun cancelJob() {
        job.cancel()
    }
}