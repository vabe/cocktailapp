package com.example.cocktail.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class Cocktail (
    @PrimaryKey
    val idDrink: String,
    val strDrink: String? = null,
    val strDrinkThumb: String? = null,
    val strVideo: String? = null,
    val strCategory: String? = null,
    val strInstructions: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null
) {
    companion object {
        fun mock() = Cocktail(
            idDrink = "11003",
            strDrink = "Negroni",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/qgdu971561574065.jpg",
            strVideo = "https://www.youtube.com/watch?v=Y4LH0FQlU8Q",
            strCategory = "Ordinary Drink",
            strInstructions = "Stir into glass over ice, garnish and serve.",
            strIngredient1 = "Gin",
            strIngredient2 = "Campari",
            strIngredient3 = "Sweet Vermouth",
            strMeasure1 = "1 oz",
            strMeasure2 = "1 oz",
            strMeasure3 = "1 oz"
        )
    }
}