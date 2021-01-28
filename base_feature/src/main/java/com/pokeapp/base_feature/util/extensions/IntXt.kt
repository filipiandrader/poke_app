package com.pokeapp.base_feature.util.extensions

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun Int.formatPokemonNumber() = when (this) {
    in 1..9 -> "#00$this"
    in 10..99 -> "#0$this"
    else -> "#$this"
}

fun Int.convertToMeter(): String {
    val meter = this / 10.0
    return "$meter m"
}

fun Int.convertToKilos(): String {
    val kilos = this / 10.0
    return "$kilos kg"
}