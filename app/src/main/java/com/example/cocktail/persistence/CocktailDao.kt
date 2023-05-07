package com.example.cocktail.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktail.model.Cocktail
import com.example.cocktail.model.CocktailResponse

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktails(cocktailsFromApi: List<Cocktail>)

    @Query("SELECT * FROM Cocktail WHERE idDrink = :id_")
    suspend fun getCocktailById(id_: String): Cocktail

    @Query("SELECT * FROM Cocktail")
    suspend fun getCocktails(): List<Cocktail>
}