package com.pokeapp.data.remote.repository

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.cache.room.repository.PokemonRoom
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.presentation.model.*
import org.json.JSONObject

/**
 * Created by Filipi Andrade on 29/03/2020
 */
class PokemonRepositoryImpl(private val api: PokemonService,
                            private val pokemonRoom: PokemonRoom) : PokemonRepository {

    override suspend fun getAllPokemon(offset: Int): ResultRequest<MutableList<Pokemon>> {
        val response = api.getAllPokemon(offset).await()

        if (!response.isSuccessful) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        val listPokemon = mutableListOf<Pokemon>()
        val pokemons = response.body()?.results
        for (i in 0 until pokemons?.size!!) {
            val responseById = api.getPokemon(pokemons[i].name).await()
            val jsonObject = JSONObject(responseById.body() as Map<*, *>)
            val sprites = jsonObject.getJSONObject("sprites")
            val abilitiesArr = jsonObject.getJSONArray("abilities")
            val typesArr = jsonObject.getJSONArray("types")

            val p = Pokemon()
            p.id = jsonObject.getInt("id")
            p.name = pokemons[i].name.capitalize()
            p.photo = sprites.getString("front_default")
            p.photo_shiny = sprites.getString("front_default")
            p.height = jsonObject.getInt("height")
            p.weight = jsonObject.getInt("weight")

            val abilities = mutableListOf<Ability>()
            for (j in 0 until abilitiesArr.length()) {
                abilities.add(Ability(name = abilitiesArr.getJSONObject(j).getJSONObject("ability").getString("name").capitalize()))
            }
            p.abilities = abilities

            val types = mutableListOf<Type>()
            for (j in 0 until typesArr.length()) {
                types.add(Type(name = typesArr.getJSONObject(j).getJSONObject("type").getString("name").capitalize()))
            }
            p.types = types

            val pokemonLocal = pokemonRoom.getById(p.id)
            if (pokemonLocal != null) {
                p.favorite = pokemonLocal.favorite
            }

            listPokemon.add(p)
        }
        return ResultRequest.success(listPokemon)
    }
}