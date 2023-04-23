package com.example.cocktail.network

import retrofit2.http.GET

interface CocktailService {

    @GET("cocktails")
    suspend fun getCocktails()
}