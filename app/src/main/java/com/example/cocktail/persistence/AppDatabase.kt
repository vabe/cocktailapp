package com.example.cocktail.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktail.model.Cocktail

@Database(entities = [Cocktail::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}