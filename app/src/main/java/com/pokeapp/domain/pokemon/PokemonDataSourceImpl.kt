package com.pokeapp.domain.pokemon

import com.pokeapp.data.remote.repository.PokemonRepository
import com.pokeapp.domain.pokemon.PokemonDataSource
import com.pokeapp.presentation.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Filipi Andrade on 29/03/2020
 */
class PokemonDataSourceImpl(private val repository: PokemonRepository) : PokemonDataSource {

    private var job: Job = Job()

    override fun getAllPokemons(offset: Int, onSuccess: (MutableList<Pokemon>) -> Unit, onFailure: (t: Throwable) -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllPokemon(offset)

            if (response.throwable != null || response.data == null) {
                response.throwable?.printStackTrace()
                onFailure(response.throwable as Throwable)
                return@launch
            }

            onSuccess(response.data)
        }
    }

    override fun cancelJob() {
        job.cancel()
    }
}