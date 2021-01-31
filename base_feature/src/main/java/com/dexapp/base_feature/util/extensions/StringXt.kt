package com.dexapp.base_feature.util.extensions

import java.util.*

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun String.formatPokemonName(): String {
    var nameFormated: String

    if (this.contains("-")) {
        val split = this.split("-")
        nameFormated = split[0].uppercase()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                nameFormated = "$nameFormated ${split[i].uppercase()}"
            }
        }
    } else {
        nameFormated = this.uppercase()
    }

    return nameFormated.replace("-", "")
}

fun String.formatNameMove(): String {
    var moveFormated: String

    if (this.contains("-")) {
        val split = this.split("-")
        moveFormated = split[0].uppercase()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                moveFormated = "$moveFormated ${split[i].uppercase()}"
            }
        }
    } else {
        moveFormated = this.uppercase()
    }
    return moveFormated.replace("-", "")
}

fun String.formatNameAbility(): String {
    var moveFormated: String

    if (this.contains("-")) {
        val split = this.split("-")
        moveFormated = split[0].uppercase()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                moveFormated = "$moveFormated ${split[i].uppercase()}"
            }
        }
    } else {
        moveFormated = this.uppercase()
    }
    return moveFormated.replace("-", "")
}

fun String.formatNameRegion(): String {
    var formated: String

    if (this.contains("-")) {
        val split = this.split("-")
        formated = split[0].replace("-", "").uppercase()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                formated = "$formated ${split[i].uppercase()}"
            }
        }
    } else {
        formated = this.uppercase()
    }
    return formated.replace("-", "")
}

fun String.uppercase(): String {
    return this.capitalize(Locale("pt", "BR"))
}

fun String.lowercase(): String {
    return this.toLowerCase(Locale("pt", "BR"))
}