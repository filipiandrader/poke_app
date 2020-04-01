package com.pokeapp.util

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pokeapp.data.cache.entities.*
import com.pokeapp.presentation.model.*

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

fun MutableList<PokemonLocal>.convertToPokemonList(): MutableList<Pokemon> {
    val list = mutableListOf<Pokemon>()
    this.forEach { listApi -> list.add(listApi.convertPokemon()) }
    return list
}

fun PokemonLocal.convertPokemon(): Pokemon =
        Pokemon(
                id = id,
                name = name,
                photo = photo,
                photo_shiny = photo_shiny,
                base_experience = base_experience,
                height = height,
                weight = weight,
                types = types.convertToTypeList(),
                abilities = abilities.convertToAbilityList(),
                moves = moves.convertToMovesList(),
                stats = stats.convertToStatsList(),
                evolves = evolves.convertToSpeciesList(),
                favorite = favorite)

fun MutableList<TypeLocal>.convertToTypeList(): MutableList<Type> {
    val list = mutableListOf<Type>()
    this.forEach { listApi -> list.add(listApi.convertType()) }
    return list
}

fun TypeLocal.convertType(): Type =
        Type(name = name)

fun MutableList<AbilityLocal>.convertToAbilityList(): MutableList<Ability> {
    val list = mutableListOf<Ability>()
    this.forEach { listApi -> list.add(listApi.convertAbility()) }
    return list
}

fun AbilityLocal.convertAbility(): Ability =
        Ability(name = name)

fun MutableList<MoveLocal>.convertToMovesList(): MutableList<Move> {
    val list = mutableListOf<Move>()
    this.forEach { listApi -> list.add(listApi.convertMove()) }
    return list
}

fun MoveLocal.convertMove(): Move =
        Move(name = name)

fun MutableList<StatsLocal>.convertToStatsList(): MutableList<Stats> {
    val list = mutableListOf<Stats>()
    this.forEach { listApi -> list.add(listApi.convertStats()) }
    return list
}

fun StatsLocal.convertStats(): Stats =
        Stats(name = name, base_state = base_state)

fun MutableList<SpeciesLocal>.convertToSpeciesList(): MutableList<Species> {
    val list = mutableListOf<Species>()
    this.forEach { listApi -> list.add(listApi.convertSpecies()) }
    return list
}

fun SpeciesLocal.convertSpecies(): Species =
        Species(name = name, photo = photo)