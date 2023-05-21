package com.example.cocktail.ui.home

import android.view.View
import com.example.cocktail.model.Cocktail

interface HomeViewClickListener {
    fun onRecyclerViewItemClick(view: View, cocktail: Cocktail)
}