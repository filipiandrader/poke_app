package com.pokeapp.ui.fragments.region_details

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pokeapp.R
import com.pokeapp.presentation.model.Region
import com.pokeapp.util.PokemonColorUtil
import com.pokeapp.util.formatGenerationName
import com.pokeapp.util.putText
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import kotlinx.android.synthetic.main.fragment_region_details.*
import kotlinx.android.synthetic.main.fragment_region_details.pokemonDetailsAppBarLayout
import kotlinx.android.synthetic.main.fragment_region_details.pokemonDetailsCollapsingToolbarLayout
import org.jetbrains.anko.backgroundColor

/**
 * A simple [Fragment] subclass.
 */
class RegionDetailsFragment : Fragment() {

    private lateinit var mRegion: Region

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRegion = checkNotNull(arguments?.getSerializable("region") as Region)

        val color = PokemonColorUtil(view.context).getCardViewColor(mRegion.name)
        pokemonDetailsAppBarLayout.backgroundColor = color
        pokemonDetailsCollapsingToolbarLayout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        activity?.window?.statusBarColor = color

        regionDetailsNameTextView.putText(mRegion.name.capitalize())
        regionDetailsGenerationTextView.putText(mRegion.main_generation.formatGenerationName())
    }
}
