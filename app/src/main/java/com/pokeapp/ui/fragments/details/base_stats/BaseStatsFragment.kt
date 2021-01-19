package com.pokeapp.ui.fragments.details.base_stats

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.R
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.PokemonBinding
import com.pokeapp.base_presentation.model.StatsBinding
import kotlinx.android.synthetic.main.fragment_base_stats.*


class BaseStatsFragment : Fragment() {

    private lateinit var mPokemon: PokemonBinding

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonBinding) = BaseStatsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPokemon = checkNotNull(arguments?.getSerializable("pokemon") as PokemonBinding)
        setupRecyclerView(mPokemon.stats)
    }

    private fun setupRecyclerView(stats: MutableList<StatsBinding>) {
        if (stats.isNotEmpty()) {
            baseStatsRecyclerView.setup {
                withDataSource(dataSourceOf(stats))
                withItem<StatsBinding, BaseStatsViewHolder>(R.layout.item_base_stats) {
                    onBind(::BaseStatsViewHolder) { _, item ->
                        this.baseStatsLabelTextView.putText(item.name.formatNameStats( requireContext()))
                        this.baseStatsTextView.putText("${item.base_state}")
                        if (item.name == "total") {
                            this.baseStatsTotalProgressBar.putProgress(item.base_state)
                            this.baseStatsTotalProgressBar.setVisible(true)
                            this.baseStatsProgressBar.setVisible(false)

                            val color = requireContext().getPokemonColor(mPokemon.types)
                            this.baseStatsTotalProgressBar.progressTintList = ColorStateList.valueOf(color)
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
