package com.example.cocktail.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktail.R
import com.example.cocktail.databinding.FragmentDetailsBinding
import com.example.cocktail.model.Cocktail
import com.example.cocktail.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder
import kotlin.reflect.KProperty1


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)


        _binding = FragmentDetailsBinding.inflate(inflater, container, false)


        homeViewModel.cocktail.observe(viewLifecycleOwner) {
            binding.detailsTitle.text = it.strDrink
            loadImage(binding.detailsImage, it.strDrinkThumb!!)
            binding.detailsInstructions.text = it.strInstructions

            val ingredients = mutableListOf<String>()
            val amounts = mutableListOf<String>()
            val combinedList = StringBuilder()

            for (prop in Cocktail::class.members) {
                if (prop.name.startsWith("strIngredient")) {
                    val instanceValue = readInstanceProperty<String>(it, prop.name)
                    if (instanceValue != "") {
                        ingredients += instanceValue
                    }
                }

                if (prop.name.startsWith("strMeasure")) {
                    var instanceValue = readInstanceProperty(it, prop.name) as String
                    if (instanceValue != "") {
                        amounts += instanceValue
                    }
                }
            }

            ingredients.forEachIndexed { index, s ->
                combinedList.append("${amounts[index]} $s ${System.getProperty("line.separator")}")
            }

            binding.detailsIngredients.text = combinedList.toString()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Suppress("UNCHECKED_CAST")
    private fun <R> readInstanceProperty(instance: Any, propertyName: String): R {
        val property = instance::class.members
            // don't cast here to <Any, R>, it would succeed silently
            .first { it.name == propertyName } as KProperty1<Any, *>
        // force a invalid cast exception if incorrect type here
        return property.get(instance) as R
    }
}