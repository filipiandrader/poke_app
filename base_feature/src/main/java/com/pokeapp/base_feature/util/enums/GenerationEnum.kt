package com.pokeapp.base_feature.util.enums

import com.pokeapp.base_feature.R

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

enum class GenerationEnum(val info: String) {
    GENERATION_I("generation-i"),
    GENERATION_II("generation-ii"),
    GENERATION_III("generation-iii"),
    GENERATION_IV("generation-iv"),
    GENERATION_V("generation-v"),
    GENERATION_VI("generation-vi"),
    GENERATION_VII("generation-vii"),
    GENERATION_VIII("generation-viii"),
    NONE("");

    companion object {

        fun getIcon(info: String) = when (info) {
            GENERATION_I.info -> R.drawable.gen1
            GENERATION_II.info -> R.drawable.gen2
            GENERATION_III.info -> R.drawable.gen3
            GENERATION_IV.info -> R.drawable.gen4
            GENERATION_V.info -> R.drawable.gen5
            GENERATION_VI.info -> R.drawable.gen6
            GENERATION_VII.info -> R.drawable.gen7
            GENERATION_VIII.info -> R.drawable.gen8
            else -> -1
        }

        fun getName(info: String) = when (info) {
            GENERATION_I.info -> R.string.first_generation_name
            GENERATION_II.info -> R.string.second_generation_name
            GENERATION_III.info -> R.string.third_generation_name
            GENERATION_IV.info -> R.string.fourth_generation_name
            GENERATION_V.info -> R.string.fifth_generation_name
            GENERATION_VI.info -> R.string.sixth_generation_name
            GENERATION_VII.info -> R.string.seventh_generation_name
            GENERATION_VIII.info -> R.string.eighth_generation_name
            else -> -1
        }
    }
}