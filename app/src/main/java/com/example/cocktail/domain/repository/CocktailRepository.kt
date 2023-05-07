package com.example.cocktail.domain.repository

import com.example.cocktail.model.Cocktail
import com.example.cocktail.model.CocktailResponse

interface CocktailRepository {
    suspend fun getCocktails(): List<Cocktail>
    suspend fun getCocktailById(cocktailId: String): Cocktail

    suspend fun findCocktailsByIngredient(ingredient: String): List<Cocktail>
}