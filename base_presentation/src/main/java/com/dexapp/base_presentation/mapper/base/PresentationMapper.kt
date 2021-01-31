package com.dexapp.base_presentation.mapper.base

interface PresentationMapper<P, D> {

    fun toDomain(presentation: P): D

    fun fromDomain(domain: D): P

    fun toDomain(presentation: List<P>): List<D> {
        return presentation.map { toDomain(it) }
    }

    fun fromDomain(domain: List<D>): List<P> {
        return domain.map { fromDomain(it) }
    }

}