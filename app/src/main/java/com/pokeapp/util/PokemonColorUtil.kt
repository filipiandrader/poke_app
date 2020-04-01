package com.pokeapp.util

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.pokeapp.R
import com.pokeapp.presentation.model.Type
import java.util.*

/**
 * Created by Filipi Andrade on 30/03/2020
 */
class PokemonColorUtil(var context: Context) {

    @ColorInt
    fun getPokemonColor(typeOfPokemon: List<Type>): Int {
        val type = typeOfPokemon.getOrNull(typeOfPokemon.size - 1)
        val color = when (type?.name?.toLowerCase(Locale("pt", "BR"))) {
            "grass", "bug", "pokedex" -> R.color.lightTeal
            "fire" -> R.color.lightRed
            "water", "fighting", "normal", "favoridex" -> R.color.lightBlue
            "electric", "psychic" -> R.color.lightYellow
            "poison", "ghost" -> R.color.lightPurple
            "ground", "rock" -> R.color.lightBrown
            "dark" -> R.color.black
            else -> R.color.lightBlue
        }
        return convertColor(color)
    }

    @ColorInt
    fun getCardViewColor(menuType: String): Int {
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
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}