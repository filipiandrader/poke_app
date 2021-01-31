package com.dexapp.base_presentation.mapper.group

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.model.group.GroupsBinding
import com.dexapp.domain.model.groups.Groups

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

object GroupMapper : PresentationMapper<GroupsBinding, Groups> {

    fun listFromDomain(groups: List<Groups>) = groups.map { fromDomain(it) }

    fun listToDomain(groups: List<GroupsBinding>) = groups.map { toDomain(it) }

    override fun toDomain(presentation: GroupsBinding) = Groups(
        name = presentation.name
    )

    override fun fromDomain(domain: Groups) = GroupsBinding(
        name = domain.name
    )
}