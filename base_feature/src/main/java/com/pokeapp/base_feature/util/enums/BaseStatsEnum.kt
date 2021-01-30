package com.pokeapp.base_feature.util.enums

import android.content.Context
import com.pokeapp.base_feature.R

/*
 * Created by Filipi Andrade Rocha on 30/01/2021.
 */

enum class BaseStatsEnum(val stats: String) {
    SPEED("speed"),
    SPECIAL_DEFENSE("special-defense"),
    SPECIAL_ATTACK("special-attack"),
    DEFENSE("defense"),
    ATTACK("attack"),
    HP("hp"),
    TOTAL("total"),
    NONE("");

    companion object {
        fun getStats(context: Context, stats: String) = when (stats) {
            SPEED.stats -> context.getString(R.string.base_stats_speed_label)
            SPECIAL_DEFENSE.stats -> context.resources.getString(R.string.base_stats_special_defense_label)
            SPECIAL_ATTACK.stats -> context.resources.getString(R.string.base_stats_special_attack_label)
            DEFENSE.stats -> context.resources.getString(R.string.base_stats_defense_label)
            ATTACK.stats -> context.resources.getString(R.string.base_stats_attack_label)
            HP.stats -> context.resources.getString(R.string.base_stats_hp_label)
            TOTAL.stats -> context.resources.getString(R.string.base_stats_total_label)
            else -> NONE.stats
        }
    }
}