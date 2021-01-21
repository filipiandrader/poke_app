package com.pokeapp.base_feature.util.enums

import com.pokeapp.base_feature.util.extensions.lowercase

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

enum class PokemonTypeEnum(val type: String) {
    NORMAL("Normal"),
    FIGHTING("Lutador"),
    FLYING("Voador"),
    POISON("Venenoso"),
    GROUND("Terra"),
    ROCK("Pedra"),
    BUG("Inseto"),
    GHOST("Fantasma"),
    STEEL("Metal"),
    FIRE("Fogo"),
    WATER("Água"),
    GRASS("Grama"),
    ELECTRIC("Elétrico"),
    PSYCHIC("Psíquico"),
    ICE("Gelo"),
    DRAGON("Dragão"),
    DARK("Sombrio"),
    FAIRY("Fada"),
    UNKNOWN("Desconhecido"),
    SHADOW("Corrompidos"),
    NONE("");

    companion object {
        fun match(type: String?) = when(type?.lowercase()) {
            NORMAL.name.lowercase() -> NORMAL.type
            FIGHTING.name.lowercase() -> FIGHTING.type
            FLYING.name.lowercase() -> FLYING.type
            POISON.name.lowercase() -> POISON.type
            GROUND.name.lowercase() -> GROUND.type
            ROCK.name.lowercase() -> ROCK.type
            BUG.name.lowercase() -> BUG.type
            GHOST.name.lowercase() -> GHOST.type
            STEEL.name.lowercase() -> STEEL.type
            FIRE.name.lowercase() -> FIRE.type
            WATER.name.lowercase() -> WATER.type
            GRASS.name.lowercase() -> GRASS.type
            ELECTRIC.name.lowercase() -> ELECTRIC.type
            PSYCHIC.name.lowercase() -> PSYCHIC.type
            ICE.name.lowercase() -> ICE.type
            DRAGON.name.lowercase() -> DRAGON.type
            DARK.name.lowercase() -> DARK.type
            FAIRY.name.lowercase() -> FAIRY.type
            UNKNOWN.name.lowercase() -> UNKNOWN.type
            SHADOW.name.lowercase() -> SHADOW.type
            else -> NONE.type
        }
    }
}