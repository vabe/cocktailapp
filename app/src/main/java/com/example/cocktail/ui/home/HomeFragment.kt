package com.example.cocktail.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktail.R
import com.example.cocktail.databinding.FragmentHomeBinding

import com.example.cocktail.model.Cocktail
import com.example.cocktail.ui.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), HomeViewClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.cocktails.observe(viewLifecycleOwner, Observer {cocktails ->
            binding.homeRecyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = HomeViewAdapter(cocktails, this)
            }
        })

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.homeSearchButton.setOnClickListener{
            val searchQuery = binding.homeSearchInput.text.toString()

            if (searchQuery == "") {
                homeViewModel.revertCocktailsToDefault()
            } else {
                homeViewModel.updateCocktailsByIngredient(searchQuery)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClick(view: View, cocktail: Cocktail) {
        val bundle = Bundle()
        bundle.putString("cocktailId", cocktail.idDrink)

        val fragment = DetailsFragment()
        fragment.arguments = bundle

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, fragment)
            .addToBackStack("cocktails")
            .commit()
    }
}