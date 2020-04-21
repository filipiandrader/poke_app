package com.pokeapp.data.remote.repository.pokemon

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.cache.room.repository.pokemon.PokemonRoom
import com.pokeapp.data.remote.model.TypeResponse
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Type
import com.pokeapp.util.convertToPokemon
import com.pokeapp.util.formatNamePokemon
import com.pokeapp.util.getPokemonID
import com.pokeapp.util.verifyResponseResult
import org.json.JSONObject

/**
 * Created by Filipi Andrade on 29/03/2020
 */
class PokemonRepositoryImpl(
    private val api: PokemonService,
    private val pokemonRoom: PokemonRoom
) : PokemonRepository {

    override suspend fun getAllPokemon(offset: Int): ResultRequest<MutableList<Pokemon>> {
        val response = api.getAllPokemon(offset)

        if (!response.isSuccessful) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        val listPokemon = mutableListOf<Pokemon>()
        val pokemons = response.body()?.results
        for (i in 0 until pokemons?.size!!) {
            val responseById = api.getPokemon(pokemons[i].name)
            val jsonObject = JSONObject(responseById.body() as Map<*, *>)
            val sprites = jsonObject.getJSONObject("sprites")
            val typesArr = jsonObject.getJSONArray("types")

            val p = Pokemon()
            p.id = jsonObject.getInt("id")
            p.name = pokemons[i].name.formatNamePokemon()
            p.photo = sprites.getString("front_default")
            p.photo_shiny = sprites.getString("front_shiny")

            val types = mutableListOf<Type>()
            for (j in 0 until typesArr.length()) {
                types.add(
                    Type(
                        name = typesArr.getJSONObject(j).getJSONObject("type").getString("name")
                    )
                )
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
        val response = api.getPokemomByGeneration(id)

        if (!response.verifyResponseResult()) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        val pokemon = mutableListOf<Pokemon>()
        for (i in 0 until response.body()!!.pokemon_species.size) {
            val obj = response.body()!!.pokemon_species[i]

            val responseByName = api.getPokemonById(obj.url.getPokemonID())

            if (!responseByName.verifyResponseResult()) {
                return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.errorBody()}"))
            }

            val p = Pokemon()

            val objPkm = responseByName.body()!!.convertToPokemon()
            p.name = obj.name.formatNamePokemon()
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

    override suspend fun getAllTypes(): ResultRequest<TypeResponse> {
        val response = api.getType()

        if (!response.verifyResponseResult()) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        return ResultRequest.success(response.body()!!)
    }

    override suspend fun getPokemonByType(id: Int): ResultRequest<MutableList<HashMap<String, Any>>> {
        val response = api.getPokemonByType(id)

        if (!response.verifyResponseResult()) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        val pokemonArr = JSONObject(response.body() as Map<*, *>).getJSONArray("pokemon")
        val result = mutableListOf<HashMap<String, Any>>()
        for (i in 0 until pokemonArr.length()) {
            val obj = pokemonArr.getJSONObject(i).getJSONObject("pokemon")
            val pokemonId = obj.getString("url").getPokemonID()

            val responseInfo = api.getPokemonById(pokemonId)

            if (!responseInfo.verifyResponseResult()) {
                return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
            }

            val objInfo = JSONObject(responseInfo.body() as Map<*, *>)
            val name = objInfo.getString("name").formatNamePokemon()
            val sprites = objInfo.getJSONObject("sprites")
            val types = objInfo.getJSONArray("types")

            val pokemonLocal = pokemonRoom.getById(pokemonId)
            val favorite = pokemonLocal != null

            val hashMapResult = hashMapOf(
                "id" to pokemonId,
                "name" to name,
                "sprites" to sprites,
                "types" to types,
                "favorite" to favorite
            )

            result.add(hashMapResult)
        }

        return ResultRequest.success(result)

    }
}