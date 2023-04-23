package com.example.cocktail.domain.repository

interface CocktailRepository {
    suspend fun getCocktails()
    suspend fun getCocktailById(id: Long)
}