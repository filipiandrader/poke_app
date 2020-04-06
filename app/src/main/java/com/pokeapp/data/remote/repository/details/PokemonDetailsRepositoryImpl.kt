package com.pokeapp.data.remote.repository.details

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Species
import com.pokeapp.util.convertToPokemon
import com.pokeapp.util.getEvolutionChainID
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by Filipi Andrade on 04/04/2020
 */

class PokemonDetailsRepositoryImpl (private val api: PokemonService) : PokemonDetailsRepository {

    override suspend fun getPokemonInfo(id: Int): ResultRequest<Pokemon> {
        val response = api.getPokemonById(id).await()

        if (!verifyResponseResult(response)) {
            return ResultRequest.error(Exception("Erro ao obter info do pokemon!"))
        }

        val pokemon = Pokemon()
        val objPkm = response.body()!!.convertToPokemon()
        pokemon.height = objPkm.height
        pokemon.weight = objPkm.weight
        pokemon.base_experience = objPkm.base_experience
        pokemon.abilities = objPkm.abilities
        pokemon.moves = objPkm.moves
        pokemon.stats = objPkm.stats

        val responseSpecies = api.getPokemonSpecies(id).await()

        if (!verifyResponseResult(responseSpecies)) {
            return ResultRequest.error(Exception("Erro ao obter info do pokemon!"))
        }

        val evolutionChainId = responseSpecies.body()!!.getEvolutionChainID()
        val responseEvolutionChain = api.getPokemonEvolutionChain(evolutionChainId).await()

        if (!verifyResponseResult(responseEvolutionChain)) {
            return ResultRequest.error(Exception("Erro ao obter info do pokemon!"))
        }

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

        pokemon.evolves = evolves

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