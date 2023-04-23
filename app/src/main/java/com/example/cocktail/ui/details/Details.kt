package com.example.cocktail.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun Details(
    cocktailId: Long,
    viewModel: DetailsViewModel,
    pressOnBack: () -> Unit = {}
) {
    LaunchedEffect(key1 = cocktailId) {
        viewModel.loadCocktailById(cocktailId)
    }
}