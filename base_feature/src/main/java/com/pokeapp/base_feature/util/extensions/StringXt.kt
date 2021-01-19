package com.pokeapp.base_feature.util.extensions

import android.annotation.SuppressLint
import android.content.Context
import com.pokeapp.base_feature.R
import java.util.*

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun setTypeName(type: String?): String {
    return when (type?.toLowerCase(Locale("pt", "BR"))) {
        "normal" -> "Normal"
        "fighting" -> "Lutador"
        "flying" -> "Voador"
        "poison" -> "Venenoso"
        "ground" -> "Terra"
        "rock" -> "Pedra"
        "bug" -> "Inseto"
        "ghost" -> "Fantasma"
        "steel" -> "Metal"
        "fire" -> "Fogo"
        "water" -> "Água"
        "grass" -> "Grama"
        "electric" -> "Elétrico"
        "psychic" -> "Psíquico"
        "ice" -> "Gelo"
        "dragon" -> "Dragão"
        "dark" -> "Sombrio"
        "fairy" -> "Fada"
        "unknown" -> "Desconhecido"
        "shadow" -> "Corrompidos"
        else -> ""
    }
}

@SuppressLint("DefaultLocale")
fun String.formatNamePokemon(): String {
    var nameFormated: String

    if (this.contains("-")) {
        val split = this.split("-")
        nameFormated = split[0].capitalize()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                nameFormated = "$nameFormated ${split[i].capitalize()}"
            }
        }
    } else {
        nameFormated = this.capitalize()
    }

    return nameFormated.replace("-", "")
}

@SuppressLint("DefaultLocale")
fun String.formatNameMove(): String {
    var moveFormated: String

    if (this.contains("-")) {
        val split = this.split("-")
        moveFormated = split[0].capitalize()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                moveFormated = "$moveFormated ${split[i].capitalize()}"
            }
        }
    } else {
        moveFormated = this.capitalize()
    }
    return moveFormated.replace("-", "")
}

@SuppressLint("DefaultLocale")
fun String.formatNameAbility(): String {
    var moveFormated: String

    if (this.contains("-")) {
        val split = this.split("-")
        moveFormated = split[0].capitalize()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                moveFormated = "$moveFormated ${split[i].capitalize()}"
            }
        }
    } else {
        moveFormated = this.capitalize()
    }
    return moveFormated.replace("-", "")
}

@SuppressLint("DefaultLocale")
fun String.formatNameRegion(): String {
    var formated: String

    if (this.contains("-")) {
        val split = this.split("-")
        formated = split[0].replace("-", "").capitalize()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                formated = "$formated ${split[i].capitalize()}"
            }
        }
    } else {
        formated = this.capitalize()
    }
    return formated.replace("-", "")
}

fun String.formatNameStats(context: Context): String {
    return when (this) {
        "speed" -> context.resources.getString(R.string.base_stats_speed_label)
        "special-defense" -> context.resources.getString(R.string.base_stats_special_defense_label)
        "special-attack" -> context.resources.getString(R.string.base_stats_special_attack_label)
        "defense" -> context.resources.getString(R.string.base_stats_defense_label)
        "attack" -> context.resources.getString(R.string.base_stats_attack_label)
        "hp" -> context.resources.getString(R.string.base_stats_hp_label)
        "total" -> context.resources.getString(R.string.base_stats_total_label)
        else -> ""
    }
}

fun String.formatGenerationName(): String {
    return when (this) {
        "generationName-i" -> "1° Geração"
        "generationName-ii" -> "2° Geração"
        "generationName-iii" -> "3° Geração"
        "generationName-iv" -> "4° Geração"
        "generationName-v" -> "5° Geração"
        "generationName-vi" -> "6° Geração"
        "generationName-vii" -> "7° Geração"
        else -> ""
    }
}