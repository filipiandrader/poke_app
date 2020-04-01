package com.pokeapp.data.remote.repository.region

import com.pokeapp.data.ResultRequest
import com.pokeapp.data.remote.services.PokemonService
import com.pokeapp.presentation.model.Region
import com.pokeapp.util.convertToGroupsList
import com.pokeapp.util.convertToLocationList
import com.pokeapp.util.convertToRegionInfoApi

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionRepositoryImpl(private val api: PokemonService) : RegionRepository {

    override suspend fun getRegion(): ResultRequest<MutableList<Region>> {
        val response = api.getRegion().await()

        if (!response.isSuccessful) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        val region = mutableListOf<Region>()
        if (response.body() != null) {
            val regionApi = response.body()!!.results
            for (i in 0 until regionApi.size) {
                val name = regionApi[i].name
                val r = Region()
                r.name = name

                val responseInfo = api.getRegionByName(name).await()

                if (!responseInfo.isSuccessful) {
                    return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
                }

                if (responseInfo.body() != null) {
                    val regionInfo = responseInfo.body()!!.convertToRegionInfoApi()
                    r.main_generation = regionInfo.main_generation.name
                    r.locations = regionInfo.locations.convertToLocationList()
                    r.groups = regionInfo.version_groups.convertToGroupsList()
                }

                region.add(r)
            }
        }

        return ResultRequest.success(region)
    }
}