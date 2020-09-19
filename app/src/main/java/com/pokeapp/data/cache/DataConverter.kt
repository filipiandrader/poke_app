package com.pokeapp.data.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pokeapp.data.cache.entities.*

/**
 * Created by Filipi Andrade Rocha on 30/01/2020
 */
class DataConverter {

    @TypeConverter
    fun toTypeList(string: String): MutableList<TypeLocal> {
        val type = object : TypeToken<MutableList<TypeLocal>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromTypeList(type: MutableList<TypeLocal>): String {
        return Gson().toJson(type)
    }

    @TypeConverter
    fun toAbilityList(string: String): MutableList<AbilityLocal> {
        val type = object : TypeToken<MutableList<AbilityLocal>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromAbilityList(ability: MutableList<AbilityLocal>): String {
        return Gson().toJson(ability)
    }

    @TypeConverter
    fun toMoveList(string: String): MutableList<MoveLocal> {
        val type = object : TypeToken<MutableList<MoveLocal>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromMoveList(move: MutableList<MoveLocal>): String {
        return Gson().toJson(move)
    }

    @TypeConverter
    fun toStatsList(string: String): MutableList<StatsLocal> {
        val type = object : TypeToken<MutableList<StatsLocal>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromStatsList(stats: MutableList<StatsLocal>): String {
        return Gson().toJson(stats)
    }

    @TypeConverter
    fun toEvolveList(string: String): MutableList<SpeciesLocal> {
        val type = object : TypeToken<MutableList<SpeciesLocal>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromEvolveList(species: MutableList<SpeciesLocal>): String {
        return Gson().toJson(species)
    }
}