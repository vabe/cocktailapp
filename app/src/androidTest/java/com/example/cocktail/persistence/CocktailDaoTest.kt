package com.example.cocktail.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.cocktail.model.Cocktail
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CocktailDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var dao: CocktailDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.cocktailDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCocktails() = runBlocking {
        val cocktail = Cocktail.mock()
        dao.insertCocktails(listOf(cocktail))

        val allCocktails = dao.getCocktails()

        assertThat(allCocktails).contains(cocktail)
    }

    @Test
    fun deleteCocktail() = runBlocking {
        val cocktail = Cocktail.mock()
        dao.insertCocktails(listOf(cocktail))
        dao.deleteCocktailById(cocktail.idDrink)

        val allCocktails = dao.getCocktails()
        assertThat(allCocktails).doesNotContain(cocktail)
    }

    @Test
    fun getCocktailById() = runBlocking {
        val cocktail = Cocktail.mock()
        dao.insertCocktails(listOf(cocktail))

        val cocktailReceived = dao.getCocktailById(cocktail.idDrink)

        assertThat(cocktailReceived).isEqualTo(cocktail)
    }

    @Test
    fun insertSameCocktail() = runBlocking {
        val cocktail = Cocktail.mock()
        dao.insertCocktails(listOf(cocktail))
        dao.insertCocktails(listOf(cocktail))

        val allCocktails = dao.getCocktails()

        assertThat(allCocktails.size).isEqualTo(1)
    }
}