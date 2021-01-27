package com.pokeapp.data_local.base

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pokeapp.data_local.model.ability.AbilityLocal
import com.pokeapp.data_local.model.evolution.EvolutionLocal
import com.pokeapp.data_local.model.move.MoveLocal
import com.pokeapp.data_local.model.stats.StatsLocal
import com.pokeapp.data_local.model.type.TypeLocal

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
    fun toEvolveList(string: String): MutableList<EvolutionLocal> {
        val type = object : TypeToken<MutableList<EvolutionLocal>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromEvolveList(evolutions: MutableList<EvolutionLocal>): String {
        return Gson().toJson(evolutions)
    }
}