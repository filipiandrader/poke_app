package com.dexapp.data_local.mapper.base

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

interface DataLocalMapper<L, D> {

    fun toLocal(domain: D): L

    fun fromLocal(local: L): D

    fun toLocal(domain: List<D>): List<L> {
        return domain.map { toLocal(it) }
    }

    fun fromLocal(local: List<L>): List<D> {
        return local.map { fromLocal(it) }
    }
}