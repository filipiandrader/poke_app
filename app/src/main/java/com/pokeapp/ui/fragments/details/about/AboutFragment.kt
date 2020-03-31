package com.pokeapp.ui.fragments.details.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pokeapp.R
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.util.convertToCentimeter
import com.pokeapp.util.convertToKilos
import com.pokeapp.util.putText
import kotlinx.android.synthetic.main.fragment_about.*

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(pokemon: Pokemon) = AboutFragment().apply {
            arguments = Bundle().apply {
                putSerializable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemon = checkNotNull(arguments?.getSerializable("pokemon") as Pokemon)
        pokemon.let { p ->
            aboutHeightTextView.putText(p.height.convertToCentimeter())
            aboutWeightTextView.putText(p.weight.convertToKilos())
            aboutBaseExpTextView.putText("${p.base_experience}")
        }
    }

}
