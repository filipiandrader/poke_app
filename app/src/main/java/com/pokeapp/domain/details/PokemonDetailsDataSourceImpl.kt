package com.pokeapp.domain.details

import com.pokeapp.data.cache.room.repository.PokemonRoom
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.util.convertPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class PokemonDetailsDataSourceImpl(private val pokemonRoom: PokemonRoom) : PokemonDetailsDataSource {

    private var job: Job = Job()

    override fun doFavoritePokemon(pokemon: Pokemon, onSuccess: () -> Unit, onFailure: () -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = pokemonRoom.insert(pokemon.convertPokemon())

            if (response) {
                onSuccess()
            } else {
                onFailure()
            }
        }
    }

    override fun cancelJob() {
        job.cancel()
    }
}