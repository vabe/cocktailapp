package com.example.cocktail.ui.home

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktail.R
import com.example.cocktail.databinding.FragmentHomeBinding
import com.example.cocktail.model.Cocktail
import com.example.cocktail.ui.details.DetailsFragment
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class HomeFragment : Fragment(), HomeViewClickListener {

    lateinit var firebaseAnalytics: FirebaseAnalytics
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        viewLifecycleOwner.lifecycleScope.launch {
            adId = getAdId()
        }


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
        // Android ID not recommended, use AdvertisingIdClient
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param("cocktailId", cocktail.idDrink)
            param("device", adId)
        }

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
    private suspend fun getAdId(): String {
        return withContext(Dispatchers.Default) {
            try {
                AdvertisingIdClient.getAdvertisingIdInfo(requireContext()).id
            } catch (exception: Exception) {
                "N/A" // there still can be an exception for other reasons but not for thread issue
            }.toString()
        }
    }

}