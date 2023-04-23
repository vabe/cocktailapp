package com.example.cocktail.data.repository

import android.app.Application
import com.example.cocktail.R
import com.example.cocktail.domain.repository.CocktailRepository
import com.example.cocktail.network.CocktailService

class CocktailRepositoryImpl(
    private val cocktailService: CocktailService,
    private val application: Application
): CocktailRepository {

    init {
        val appName = application.getString(R.string.app_name)
        println("hello from $appName")
    }

    override suspend fun getCocktails() {
        TODO("Not yet implemented")
    }

    override suspend fun getCocktailById(id: Long) {
        TODO("Not yet implemented")
    }
}