package com.dexapp.base_feature.util.extensions

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.dexapp.base_feature.R
import java.util.*

/**
 * Created by Filipi Andrade on 30/03/2020
 */

@ColorInt
fun Context.getPokemonColor(typeOfPokemon: String): Int {
    val color = when (typeOfPokemon.toLowerCase(Locale("pt", "BR"))) {
        "grass", "bug" -> R.color.lightTeal
        "fire" -> R.color.lightRed
        "water", "fighting", "normal" -> R.color.lightBlue
        "electric", "psychic" -> R.color.lightYellow
        "poison", "ghost" -> R.color.lightPurple
        "ground", "rock" -> R.color.lightBrown
        "dark" -> R.color.black
        else -> R.color.lightBlue
    }
    return convertColor(color)
}

@ColorInt
fun Context.getCardViewColor(menuType: String): Int {
    val color = when (menuType.toLowerCase(Locale("pt", "BR"))) {
        "pokedex", "kanto" -> R.color.lightTeal
        "favoridex", "johto" -> R.color.lightBlue
        "region", "hoenn" -> R.color.lightRed
        "sinnoh" -> R.color.lightYellow
        "unova" -> R.color.lightPurple
        "kalos" -> R.color.lightBrown
        "alola" -> R.color.black
        else -> R.color.lightBlue
    }
    return convertColor(color)
}

@ColorInt
fun Context.convertColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}