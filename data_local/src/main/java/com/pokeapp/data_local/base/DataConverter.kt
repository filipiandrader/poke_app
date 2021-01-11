package com.pokeapp.data_local.base

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pokeapp.domain.model.*

/**
 * Created by Filipi Andrade Rocha on 30/01/2020
 */
class DataConverter {

    @TypeConverter
    fun toTypeList(string: String): MutableList<Type> {
        val type = object : TypeToken<MutableList<Type>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromTypeList(type: MutableList<Type>): String {
        return Gson().toJson(type)
    }

    @TypeConverter
    fun toAbilityList(string: String): MutableList<Ability> {
        val type = object : TypeToken<MutableList<Ability>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromAbilityList(ability: MutableList<Ability>): String {
        return Gson().toJson(ability)
    }

    @TypeConverter
    fun toMoveList(string: String): MutableList<Move> {
        val type = object : TypeToken<MutableList<Move>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromMoveList(move: MutableList<Move>): String {
        return Gson().toJson(move)
    }

    @TypeConverter
    fun toStatsList(string: String): MutableList<Stats> {
        val type = object : TypeToken<MutableList<Stats>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromStatsList(stats: MutableList<Stats>): String {
        return Gson().toJson(stats)
    }

    @TypeConverter
    fun toEvolveList(string: String): MutableList<Species> {
        val type = object : TypeToken<MutableList<Species>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromEvolveList(species: MutableList<Species>): String {
        return Gson().toJson(species)
    }
}