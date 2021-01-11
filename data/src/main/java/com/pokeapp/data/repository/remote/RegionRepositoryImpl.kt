package com.pokeapp.data.repository.remote

import com.pokeapp.data.ResultRequest
import com.pokeapp.data_remote.service.PokemonService
import com.pokeapp.presentation.model.Region

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionRepositoryImpl(private val api: com.pokeapp.data_remote.service.PokemonService) : com.pokeapp.data.RegionRepository {

    override suspend fun getRegion(): ResultRequest<MutableList<Region>> {
        val response = api.getRegion()

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

                val responseInfo = api.getRegionByName(name)

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