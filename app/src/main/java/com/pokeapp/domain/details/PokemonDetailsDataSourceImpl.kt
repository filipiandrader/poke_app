package com.pokeapp.domain.details

import com.pokeapp.data.cache.room.repository.pokemon.PokemonRoom
import com.pokeapp.data.remote.repository.details.PokemonDetailsRepository
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.util.convertPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class PokemonDetailsDataSourceImpl(private val repository: PokemonDetailsRepository,
                                   private val pokemonRoom: PokemonRoom) : PokemonDetailsDataSource {

    private var job: Job = Job()

    override fun getPokemonInfo(id: Int, onSuccess: (Pokemon) -> Unit, onFailure: (t: Throwable) -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPokemonInfo(id)

            if (response.throwable != null || response.data == null) {
                response.throwable?.printStackTrace()
                onFailure(response.throwable as Throwable)
                return@launch
            }

            onSuccess(response.data)
        }
    }

    override fun doFavouritePokemon(pokemon: Pokemon, onSuccess: () -> Unit, onFailure: () -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = if (pokemon.favourite) {
                pokemonRoom.insert(pokemon.convertPokemon())
            } else {
                pokemonRoom.delete(pokemon.convertPokemon())
            }

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