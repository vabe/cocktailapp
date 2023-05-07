package com.example.cocktail.model

data class CocktailResponse (
    val drinks: List<Cocktail>
) {
    companion object {
        fun mock() = CocktailResponse(drinks =  listOf())
    }
}