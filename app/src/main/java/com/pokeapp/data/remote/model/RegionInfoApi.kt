package com.pokeapp.data.remote.model

/**
 * Created by Filipi Andrade on 01/04/2020
 */
class RegionInfoApi(var main_generation: MainGenerationApi = MainGenerationApi(),
                    var locations: MutableList<LocationApi> = mutableListOf(),
                    var version_groups: MutableList<GroupsApi> = mutableListOf())