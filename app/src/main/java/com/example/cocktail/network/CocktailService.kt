package com.example.cocktail.network

import com.example.cocktail.model.CocktailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {
    @GET("search.php?s=gin")
    suspend fun getCocktails(): CocktailResponse
    @GET("lookup.php")
    suspend fun getCocktailById(@Query("i") cocktailId: String): CocktailResponse
    @GET("filter.php")
    suspend fun findCocktailsByIngredient(@Query("i") ingredient: String): CocktailResponse
}