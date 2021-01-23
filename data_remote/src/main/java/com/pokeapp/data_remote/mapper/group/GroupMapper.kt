package com.pokeapp.data_remote.mapper.group

import com.pokeapp.data_remote.model.groups.GroupsResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.groups.Groups

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object GroupMapper : DataRemoteMapper<GroupsResponse, Groups>() {

    fun listToDomain(data: List<GroupsResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: GroupsResponse) = Groups(
        name = data.name ?: ""
    )
}