package com.pokeapp.data.remote.repository.pokemon

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.cache.room.repository.PokemonRoom
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Type
import com.pokeapp.util.convertToPokemon
import org.json.JSONObject
import retrofit2.Response

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
            val typesArr = jsonObject.getJSONArray("types")

            val p = Pokemon()
            p.id = jsonObject.getInt("id")
            p.name = pokemons[i].name.capitalize()
            p.photo = sprites.getString("front_default")
            p.photo_shiny = sprites.getString("front_shiny")

            val types = mutableListOf<Type>()
            for (j in 0 until typesArr.length()) {
                types.add(Type(name = typesArr.getJSONObject(j).getJSONObject("type").getString("name").capitalize()))
            }
            p.types = types

            val pokemonLocal = pokemonRoom.getById(p.id)
            if (pokemonLocal != null) {
                p.favourite = pokemonLocal.favourite
            }

            listPokemon.add(p)
        }
        return ResultRequest.success(listPokemon)
    }

    override suspend fun getPokemonByGeneration(id: Int): ResultRequest<MutableList<Pokemon>> {
        val response = api.getPokemomByGeneration(id).await()

        if (!verifyResponseResult(response)) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        val pokemon = mutableListOf<Pokemon>()
        for (i in 0 until response.body()!!.pokemon_species.size) {
            val obj = response.body()!!.pokemon_species[i]

            val responseByName = api.getPokemon(obj.name).await()

            if (!verifyResponseResult(responseByName)) {
                return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
            }

            val p = Pokemon()

            val objPkm = responseByName.body()!!.convertToPokemon()
            p.name = obj.name.capitalize()
            p.id = objPkm.id
            p.photo = objPkm.photo
            p.photo_shiny = objPkm.photo_shiny
            p.types = objPkm.types

            val pokemonLocal = pokemonRoom.getById(p.id)
            if (pokemonLocal != null) {
                p.favourite = pokemonLocal.favourite
            }

            pokemon.add(p)
        }

        pokemon.sortBy { it.id }
        return ResultRequest.success(pokemon)
    }

    private fun verifyResponseResult(response: Response<*>) : Boolean {
        if (!response.isSuccessful) {
            return false
        } else if (response.body() == null) {
            return false
        }
        return true
    }
}