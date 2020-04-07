package com.pokeapp.ui.fragments.details.base_stats

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.R
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Stats
import com.pokeapp.util.*
import kotlinx.android.synthetic.main.fragment_base_stats.*

/**
 * A simple [Fragment] subclass.
 */
class BaseStatsFragment : Fragment() {

    private lateinit var mPokemon: Pokemon

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

        mPokemon = checkNotNull(arguments?.getSerializable("pokemon") as Pokemon)
        setupRecyclerView(mPokemon.stats)
    }

    private fun setupRecyclerView(stats: MutableList<Stats>) {
        if (stats.isNotEmpty()) {
            baseStatsRecyclerView.setup {
                withDataSource(dataSourceOf(stats))
                withItem<Stats, BaseStatsViewHolder>(R.layout.item_base_stats) {
                    onBind(::BaseStatsViewHolder) { _, item ->
                        this.baseStatsLabelTextView.putText(item.name.formatNameStats(context!!))
                        this.baseStatsTextView.putText("${item.base_state}")
                        if (item.name == "total") {
                            this.baseStatsTotalProgressBar.putProgress(item.base_state)
                            this.baseStatsTotalProgressBar.setVisible(true)
                            this.baseStatsProgressBar.setVisible(false)

                            val color = PokemonColorUtil(itemView.context).getPokemonColor(mPokemon.types)
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
