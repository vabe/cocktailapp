package com.example.cocktail.data.repository

import androidx.annotation.WorkerThread
import com.example.cocktail.domain.repository.CocktailRepository
import com.example.cocktail.model.Cocktail
import com.example.cocktail.model.CocktailResponse
import com.example.cocktail.network.CocktailService
import com.example.cocktail.persistence.CocktailDao
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailService: CocktailService,
    private val cocktailDao: CocktailDao
): CocktailRepository {
    @WorkerThread
    override suspend fun getCocktails(): List<Cocktail> {
        val cocktails = cocktailDao.getCocktails()

        if (cocktails.isNotEmpty()) {
            return cocktails
        }

        val cocktailsFromApi = cocktailService.getCocktails().drinks

        if (!cocktailsFromApi.isNullOrEmpty()) {
            cocktailDao.insertCocktails(cocktailsFromApi)
        }

        return cocktailsFromApi
    }

    @WorkerThread
    override suspend fun getCocktailById(cocktailId: String): Cocktail {
        return cocktailService.getCocktailById(cocktailId)
    }

    @WorkerThread
    override suspend fun findCocktailsByIngredient(ingredient: String): List<Cocktail> {
        return cocktailService.findCocktailsByIngredient(ingredient).drinks
    }
}