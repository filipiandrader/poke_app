package com.pokeapp.data_remote.datasource

import com.pokeapp.data_local.datasource.type.TypeRoom
import com.pokeapp.data.PokemonRepository
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Filipi Andrade on 29/03/2020
 */
class PokemonDataSourceImpl(private val repository: com.pokeapp.data.PokemonRepository,
                            private val typeRoom: TypeRoom) :
    com.pokeapp.data.datasource.remote.PokemonDataSource {

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

    override fun getPokemonByGeneration(id: Int, onSuccess: (MutableList<Pokemon>) -> Unit, onFailure: (t: Throwable) -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPokemonByGeneration(id)

            if (response.throwable != null || response.data == null) {
                response.throwable?.printStackTrace()
                onFailure(response.throwable as Throwable)
                return@launch
            }

            onSuccess(response.data)
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

    override fun getPokemonByType(id: Int, onSuccess: (MutableList<Pokemon>) -> Unit, onFailure: (t: Throwable) -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPokemonByType(id)

            if (response.throwable != null || response.data == null) {
                response.throwable?.printStackTrace()
                onFailure(response.throwable as Throwable)
                return@launch
            }

            onSuccess(response.data.convertToPokemonList2())
        }
    }

    override fun cancelJob() {
        job.cancel()
    }
}