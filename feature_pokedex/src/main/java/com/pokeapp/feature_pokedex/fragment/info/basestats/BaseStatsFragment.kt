package com.pokeapp.feature_pokedex.fragment.info.basestats

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.PokemonInfoBinding
import com.pokeapp.base_presentation.model.StatsBinding
import com.pokeapp.feature_pokedex.R
import kotlinx.android.synthetic.main.fragment_base_stats.*

class BaseStatsFragment : BaseFragment() {

    private lateinit var pokemon: PokemonInfoBinding

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonInfoBinding) = BaseStatsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
        setupRecyclerView(pokemon.stats)
    }

    private fun setupRecyclerView(stats: MutableList<StatsBinding>) {
        if (stats.isNotEmpty()) {
            baseStatsRecyclerView.setup {
                withDataSource(dataSourceOf(stats))
                withItem<StatsBinding, BaseStatsViewHolder>(R.layout.item_base_stats) {
                    onBind(::BaseStatsViewHolder) { _, item ->
                        this.baseStatsLabelTextView.putText(item.name.formatNameStats(requireContext()))
                        this.baseStatsTextView.putText("${item.base_state}")
                        if (item.name == "total") {
                            this.baseStatsTotalProgressBar.putProgress(item.base_state)
                            this.baseStatsTotalProgressBar.setVisible(true)
                            this.baseStatsProgressBar.setVisible(false)

                            val color = requireContext().getPokemonColor(pokemon.types)
                            this.baseStatsTotalProgressBar.progressTintList =
                                ColorStateList.valueOf(color)
                        } else {
                            this.baseStatsProgressBar.putProgress(item.base_state)
                            this.baseStatsProgressBar.setVisible(true)
                            this.baseStatsTotalProgressBar.setVisible(false)
                        }
                    }
                }
            }
        }
    }
}
