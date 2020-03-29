package com.pokeapp.data.remote.repository

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.presentation.model.Pokemon
import org.json.JSONObject
import timber.log.Timber

/**
 * Created by Filipi Andrade on 29/03/2020
 */
class PokemonRepositoryImpl(private val api: PokemonService) : PokemonRepository {

    override suspend fun getAllPokemon(offset: Int): ResultRequest<MutableList<Pokemon>> {
        val response = api.getAllPokemon(offset).await()

        if (!response.isSuccessful) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        val listPokemon = mutableListOf<Pokemon>()
        val pokemons = response.body()?.results
        for (i in 0 until pokemons?.size!!) {
            val responseById = api.getPokemon(pokemons[i].name).await()
            val jsonObject = JSONObject(responseById.body() as Map<*, *>).getJSONObject("sprites")

            val p = Pokemon()
            p.name = pokemons[i].name
            p.photo = jsonObject.getString("front_default")
            p.photo_shiny = jsonObject.getString("front_default")

            listPokemon.add(p)
        }
        return ResultRequest.success(listPokemon)
    }
}