package com.pokeapp.base_feature.util.extensions

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun Int.getGenerationName(): String {
    return when (this) {
        1 -> "kanto"
        2 -> "johto"
        3 -> "hoenn"
        4 -> "sinnoh"
        5 -> "unova"
        6 -> "kalos"
        7 -> "alola"
        else -> ""
    }
}

fun Int.convertToMeter(): String {
    val meter = this / 10.0
    return "$meter m"
}

fun Int.convertToKilos(): String {
    val kilos = this / 10.0
    return "$kilos kg"
}