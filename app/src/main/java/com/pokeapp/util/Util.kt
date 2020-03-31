package com.pokeapp.util

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pokeapp.data.remote.model.PokemonApi
import com.pokeapp.presentation.model.Pokemon

/**
 * Created by Filipi Andrade Rocha on 29/01/2020
 */

fun View.setVisible(visible: Boolean) = if (visible) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}

fun View.setEnable(enable: Boolean) {
    this.isEnabled = enable
}

fun TextView.putText(text: String) {
    this.text = text
}

fun ProgressBar.putProgress(progress: Int) {
    this.progress = progress
}

fun TextInputEditText.getTextString() = this.text.toString()

fun TextInputLayout.setErrorText(error: String) {
//    this.isErrorEnabled = true
    this.error = error
}

fun String.formatNameMove(): String {
    var moveFormated = ""

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
    return moveFormated
}

fun String.formatNameStats(): String {
    return when (this) {
        "speed" -> "Velocidade"
        "special-defense" -> "Defesa Especial"
        "special-attack" -> "Ataque Especial"
        "defense" -> "Defesa"
        "attack" -> "Ataque"
        "hp" -> "HP"
        else -> ""
    }
}

fun String.getEvolutionChainID(): Int {
    val split = this.split("chain/")
    return split[1].replace("/", "").toInt()
}

fun Int.convertToCentimeter(): String {
    val cm = this / 10.0
    return "$cm cm"
}

fun Int.convertToKilos(): String {
    val kilos = this / 10.0
    return "$kilos kg"
}