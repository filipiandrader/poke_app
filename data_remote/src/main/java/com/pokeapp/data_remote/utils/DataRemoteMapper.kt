package com.pokeapp.data_remote.utils

abstract class DataRemoteMapper<in R, out D> {
    abstract fun toDomain(data: R): D
}