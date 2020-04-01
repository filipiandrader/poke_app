package com.pokeapp.data.remote.repository

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.cache.room.repository.PokemonRoom
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.presentation.model.*
import com.pokeapp.util.formatNameMove
import com.pokeapp.util.getEvolutionChainID
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
            val movesArr = jsonObject.getJSONArray("moves")
            val statsArr = jsonObject.getJSONArray("stats")

            val p = Pokemon()
            p.id = jsonObject.getInt("id")
            p.name = pokemons[i].name.capitalize()
            p.photo = sprites.getString("front_default")
            p.photo_shiny = sprites.getString("front_shiny")
            p.base_experience = jsonObject.getInt("base_experience")
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

            val moves = mutableListOf<Move>()
            for (j in 0 until movesArr.length()) {
                moves.add(Move(name = movesArr.getJSONObject(j).getJSONObject("move").getString("name").formatNameMove()))
            }
            p.moves = moves

            val stats = mutableListOf<Stats>()
            var total = 0
            for (j in 0 until statsArr.length()) {
                val obj = statsArr.getJSONObject(j)
                val stat = obj.getJSONObject("stat")

                val s = Stats()
                s.base_state = obj.getInt("base_stat")
                s.name = stat.getString("name")

                stats.add(s)
                total += s.base_state
            }
            stats.reverse()
            stats.add(Stats("total", total))
            p.stats = stats

            val responseSpeciesId = api.getPokemonSpecies(p.id).await()
            val speciesObj = JSONObject(responseSpeciesId.body() as Map<*, *>)
            val evolutionChainId = speciesObj.getJSONObject("evolution_chain").getString("url").getEvolutionChainID()
            val responseEvolutionChain = api.getPokemonEvolutionChain(evolutionChainId).await()
            val chain = JSONObject(responseEvolutionChain.body() as Map<*, *>).getJSONObject("chain")
            val evolves = mutableListOf<Species>()
            val evolvesToArr = chain.getJSONArray("evolves_to")
            if (evolvesToArr.length() > 0) {
                val firstEvolve = evolvesToArr.getJSONObject(0).getJSONObject("species")
                val secondEvolveArr = evolvesToArr.getJSONObject(0).getJSONArray("evolves_to")

                val firstEvolveName = firstEvolve.getString("name")
                val firstEvolveResponse = api.getPokemon(firstEvolveName).await()
                val firstEvolveSprites = JSONObject(firstEvolveResponse.body() as Map<*, *>).getJSONObject("sprites")
                val photoFirstEvolve = firstEvolveSprites.getString("front_default")
                evolves.add(Species(name = firstEvolveName.capitalize(), photo = photoFirstEvolve))

                if (secondEvolveArr.length() > 0) {
                    val secondEvolve = secondEvolveArr.getJSONObject(0).getJSONObject("species")
                    val secondEvolveName = secondEvolve.getString("name")
                    val secondEvolveResponse = api.getPokemon(secondEvolveName).await()
                    val secondEvolveSprites = JSONObject(secondEvolveResponse.body() as Map<*, *>).getJSONObject("sprites")
                    val photoSecondEvolve = secondEvolveSprites.getString("front_default")
                    evolves.add(Species(name = secondEvolve.getString("name").capitalize(), photo = photoSecondEvolve))
                }
            }
            p.evolves = evolves

            val pokemonLocal = pokemonRoom.getById(p.id)
            if (pokemonLocal != null) {
                p.favorite = pokemonLocal.favorite
            }

            listPokemon.add(p)
        }
        return ResultRequest.success(listPokemon)
    }
}