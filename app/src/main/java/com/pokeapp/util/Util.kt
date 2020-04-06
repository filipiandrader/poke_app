package com.pokeapp.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pokeapp.R
import com.pokeapp.data.cache.entities.*
import com.pokeapp.data.remote.model.*
import com.pokeapp.presentation.model.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

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

fun SwipeRefreshLayout.setRefresh(refresh: Boolean) {
    this.isRefreshing = refresh
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
    return moveFormated.replace("-", "")
}

fun String.formatNameAbility(): String {
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
    return moveFormated.replace("-", "")
}

fun String.formatNameRegion(): String {
    var formated = ""

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

fun HashMap<String, Any>.getEvolutionChainID(): Int {
    val obj = JSONObject(this as Map<*, *>).getJSONObject("evolution_chain")
    val split = obj.getString("url").split("chain/")
    return split[1].replace("/", "").toInt()
}

fun String.getPokemonID(): Int {
    if (this.contains("species")) {
        val split = this.split("species/")
        return split[1].replace("/", "").toInt()
    }
    val split = this.split("pokemon/")
    return split[1].replace("/", "").toInt()
}

fun String.getTypeId(): Int {
    val split = this.split("type/")
    return split[1].replace("/", "").toInt()
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
        "generation-i" -> "1° Geração"
        "generation-ii" -> "2° Geração"
        "generation-iii" -> "3° Geração"
        "generation-iv" -> "4° Geração"
        "generation-v" -> "5° Geração"
        "generation-vi" -> "6° Geração"
        "generation-vii" -> "7° Geração"
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

fun HashMap<String, Any>.convertToPokemon(): Pokemon {
    val obj = JSONObject(this as Map<*, *>)
    val sprites = obj.getJSONObject("sprites")
    val abilities = obj.getJSONArray("abilities")
    val moves = obj.getJSONArray("moves")
    val stats = obj.getJSONArray("stats")
    val types = obj.getJSONArray("types")

    return Pokemon(
            id = obj.getInt("id"),
            base_experience = obj.getInt("base_experience"),
            height = obj.getInt("height"),
            weight = obj.getInt("weight"),
            photo = sprites.convertToSprite().photo,
            photo_shiny = sprites.convertToSprite().photo_shiny,
            types = types.convertToType(),
            moves = moves.convertToMove(),
            stats = stats.convertToStats(),
            abilities = abilities.convertToAbility()
    )
}

fun HashMap<String, Any>.convertToSpecie(): MutableList<Species> {
    val chain = JSONObject(this as Map<*, *>).getJSONObject("chain")
    val evolvesToArr = chain.getJSONArray("evolves_to")
    val evolves = mutableListOf<Species>()

    if (evolvesToArr.length() > 0) {
        val firstEvolve = evolvesToArr.getJSONObject(0).getJSONObject("species")

    }

    return evolves
}

fun JSONObject.convertToSprite(): SpritesApi =
        SpritesApi(
                photo = this.getString("front_default"),
                photo_shiny = this.getString("front_shiny")
        )

fun JSONArray.convertToType(): MutableList<Type> {
    val list = mutableListOf<Type>()
    for (i in 0 until this.length()) {
        val obj = this.getJSONObject(i).getJSONObject("type")
        val a = Type()
        a.name = obj.getString("name")
        list.add(a)
    }

    return list
}

fun JSONArray.convertToAbility(): MutableList<Ability> {
    val list = mutableListOf<Ability>()
    for (i in 0 until this.length()) {
        val obj = this.getJSONObject(i).getJSONObject("ability")
        val a = Ability()
        a.name = obj.getString("name")
        list.add(a)
    }

    return list
}

fun JSONArray.convertToMove(): MutableList<Move> {
    val list = mutableListOf<Move>()
    for (i in 0 until this.length()) {
        val obj = this.getJSONObject(i).getJSONObject("move")
        val a = Move()
        a.name = obj.getString("name")
        list.add(a)
    }

    return list
}

fun JSONArray.convertToStats(): MutableList<Stats> {
    val list = mutableListOf<Stats>()
    var total = 0
    for (i in 0 until this.length()) {
        val obj = this.getJSONObject(i)
        val a = Stats()
        a.base_state = obj.getInt("base_stat")
        a.name = obj.getJSONObject("stat").getString("name")
        list.add(a)
        total += a.base_state
    }
    list.add(Stats(name = "total", base_state = total))

    return list
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
                favourite = favourite
        )

fun MutableList<TypeLocal>.convertToTypeList(): MutableList<Type> {
    val list = mutableListOf<Type>()
    this.forEach { listApi -> list.add(listApi.convertType()) }
    return list
}

fun TypeLocal.convertType(): Type = Type(name = name, id = id)

fun MutableList<AbilityLocal>.convertToAbilityList(): MutableList<Ability> {
    val list = mutableListOf<Ability>()
    this.forEach { listApi -> list.add(listApi.convertAbility()) }
    return list
}

fun AbilityLocal.convertAbility(): Ability = Ability(name = name)

fun MutableList<MoveLocal>.convertToMovesList(): MutableList<Move> {
    val list = mutableListOf<Move>()
    this.forEach { listApi -> list.add(listApi.convertMove()) }
    return list
}

fun MoveLocal.convertMove(): Move = Move(name = name)

fun MutableList<StatsLocal>.convertToStatsList(): MutableList<Stats> {
    val list = mutableListOf<Stats>()
    this.forEach { listApi -> list.add(listApi.convertStats()) }
    return list
}

fun StatsLocal.convertStats(): Stats = Stats(name = name, base_state = base_state)

fun MutableList<SpeciesLocal>.convertToSpeciesList(): MutableList<Species> {
    val list = mutableListOf<Species>()
    this.forEach { listApi -> list.add(listApi.convertSpecies()) }
    return list
}

fun SpeciesLocal.convertSpecies(): Species = Species(name = name, photo = photo)

fun Pokemon.convertPokemon(): PokemonLocal =
        PokemonLocal(
                id = id,
                name = name,
                photo = photo,
                photo_shiny = photo_shiny,
                base_experience = base_experience,
                height = height,
                weight = weight,
                types = types.convertToTypeLocalList(),
                abilities = abilities.convertToAbilityLocalList(),
                moves = moves.convertToMovesLocalList(),
                stats = stats.convertToStatsLocalList(),
                evolves = evolves.convertToSpeciesLocalList(),
                favourite = favourite
        )

fun MutableList<HashMap<String, Any>>.convertToPokemonList2() : MutableList<Pokemon> {
    val list = mutableListOf<Pokemon>()
    this.forEach { p ->
        val obj = JSONObject(p as Map<*, *>)
        val sprite = obj.getJSONObject("sprites").convertToSprite()
        val types = obj.getJSONArray("types").convertToType()
        list.add(Pokemon(
                id = obj.getInt("id"),
                name = obj.getString("name"),
                favourite = obj.getBoolean("favorite"),
                photo = sprite.photo,
                photo_shiny = sprite.photo_shiny,
                types = types
        ))
    }
    return list
}

fun MutableList<Type>.convertToTypeLocalList(): MutableList<TypeLocal> {
    val list = mutableListOf<TypeLocal>()
    this.forEach { listApi -> list.add(listApi.convertType()) }
    return list
}

fun TypeResponse.convertToTypeList3(): MutableList<Type> {
    val list = mutableListOf<Type>()
    this.results.forEach { listApi -> list.add(listApi.convertType()) }
    return list
}

fun Type.convertType(): TypeLocal = TypeLocal(name = name)

fun MutableList<Ability>.convertToAbilityLocalList(): MutableList<AbilityLocal> {
    val list = mutableListOf<AbilityLocal>()
    this.forEach { listApi -> list.add(listApi.convertAbility()) }
    return list
}

fun Ability.convertAbility(): AbilityLocal = AbilityLocal(name = name)

fun MutableList<Move>.convertToMovesLocalList(): MutableList<MoveLocal> {
    val list = mutableListOf<MoveLocal>()
    this.forEach { listApi -> list.add(listApi.convertMove()) }
    return list
}

fun Move.convertMove(): MoveLocal = MoveLocal(name = name)

fun MutableList<Stats>.convertToStatsLocalList(): MutableList<StatsLocal> {
    val list = mutableListOf<StatsLocal>()
    this.forEach { listApi -> list.add(listApi.convertStats()) }
    return list
}

fun Stats.convertStats(): StatsLocal = StatsLocal(name = name, base_state = base_state)

fun MutableList<Species>.convertToSpeciesLocalList(): MutableList<SpeciesLocal> {
    val list = mutableListOf<SpeciesLocal>()
    this.forEach { listApi -> list.add(listApi.convertSpecies()) }
    return list
}

fun Species.convertSpecies(): SpeciesLocal = SpeciesLocal(name = name, photo = photo)

fun MutableList<LocationApi>.convertToLocationList(): MutableList<Location> {
    val list = mutableListOf<Location>()
    this.forEach { listApi -> list.add(listApi.convertLocation()) }
    return list
}

fun LocationApi.convertLocation(): Location = Location(name = name)

fun MutableList<GroupsApi>.convertToGroupsList(): MutableList<Groups> {
    val list = mutableListOf<Groups>()
    this.forEach { listApi -> list.add(listApi.convertGroup()) }
    return list
}

fun GroupsApi.convertGroup(): Groups = Groups(name = name)

fun HashMap<String, Any>.convertToRegionInfoApi(): RegionInfoApi {
    val jsonObj = JSONObject(this as Map<*, *>)
    val regionInfoApi = RegionInfoApi()

    val jsonMainGeneration = jsonObj.getJSONObject("main_generation")
    regionInfoApi.main_generation = jsonMainGeneration.convertMainGeneration()

    val jsonLocation = jsonObj.getJSONArray("locations")
    regionInfoApi.locations = jsonLocation.convertToLocationList()

    val jsonGroup = jsonObj.getJSONArray("version_groups")
    regionInfoApi.version_groups = jsonGroup.convertToGroupList()

    return regionInfoApi
}

fun JSONObject.convertMainGeneration(): MainGenerationApi = MainGenerationApi(name = this.getString("name"))

fun JSONArray.convertToLocationList(): MutableList<LocationApi> {
    val list = mutableListOf<LocationApi>()
    for (i in 0 until this.length()) {
        list.add(this.getJSONObject(i).convertLocation())
    }
    return list
}

fun JSONObject.convertLocation(): LocationApi = LocationApi(name = this.getString("name"))

fun JSONArray.convertToGroupList(): MutableList<GroupsApi> {
    val list = mutableListOf<GroupsApi>()
    for (i in 0 until this.length()) {
        list.add(this.getJSONObject(i).convertGroup())
    }
    return list
}

fun JSONObject.convertGroup(): GroupsApi = GroupsApi(name = this.getString("name"))

fun TypeResponse.convertToType(): MutableList<Type> {
    val list = mutableListOf<Type>()
    this.results.forEach { listApi -> list.add(listApi.convertType()) }
    return list
}

fun TypeApi.convertType() = Type(id = url.getTypeId(), name = name)

fun TypeApi.convertToTypeLocal() = TypeLocal(id = url.getTypeId(), name = name)

fun Response<*>.verifyResponseResult(): Boolean {
    if (!this.isSuccessful) {
        return false
    } else if (this.body() == null) {
        return false
    }
    return true
}