package com.pokeapp.ui.fragments.details.base_stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pokeapp.R
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.util.putProgress
import com.pokeapp.util.putText
import kotlinx.android.synthetic.main.fragment_base_stats.*

/**
 * A simple [Fragment] subclass.
 */
class BaseStatsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(pokemon: Pokemon) = BaseStatsFragment().apply {
            arguments = Bundle().apply {
                putSerializable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemon = checkNotNull(arguments?.getSerializable("pokemon") as Pokemon)
        pokemon.let { p ->
            var total = 0
            for (i in p.stats.indices) {
                total += p.stats[i].base_state
                when (p.stats[i].name) {
                    "speed" -> {
                        baseStatsSpeedTextView.putText("${p.stats[i].base_state}")
                        baseStatsSpeedProgressBar.putProgress(p.stats[i].base_state)
                    }
                    "special-defense" -> {
                        baseStatsSpDefTextView.putText("${p.stats[i].base_state}")
                        baseStatsSpDefProgressBar.putProgress(p.stats[i].base_state)
                    }
                    "special-attack" -> {
                        baseStatsSpAtkTextView.putText("${p.stats[i].base_state}")
                        baseStatsSpAtkProgressBar.putProgress(p.stats[i].base_state)
                    }
                    "defense" -> {
                        baseStatsDefTextView.putText("${p.stats[i].base_state}")
                        baseStatsDefProgressBar.putProgress(p.stats[i].base_state)
                    }
                    "attack" -> {
                        baseStatsAtkTextView.putText("${p.stats[i].base_state}")
                        baseStatsAtkProgressBar.putProgress(p.stats[i].base_state)
                    }
                    "hp" -> {
                        baseStatsHPTextView.putText("${p.stats[i].base_state}")
                        baseStatsHPProgressBar.putProgress(p.stats[i].base_state)
                    }
                    else -> {
                        // IGNORE
                    }
                }
            }

            baseStatsTotalTextView.putText("$total")
            baseStatsTotalProgressBar.putProgress(total)
        }
    }

}
