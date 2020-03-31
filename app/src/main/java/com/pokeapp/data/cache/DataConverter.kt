package com.pokeapp.data.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pokeapp.data.cache.entities.Ability
import com.pokeapp.data.cache.entities.Type

/**
 * Created by Filipi Andrade Rocha on 30/01/2020
 */
class DataConverter {

    @TypeConverter
    fun toType(string: String): Type {
        val type = object : TypeToken<Type>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromType(type: Type): String {
        return Gson().toJson(type)
    }

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
    fun toAbility(string: String): Ability {
        val type = object : TypeToken<Ability>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromAbility(ability: Ability): String {
        return Gson().toJson(ability)
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
}