package com.example.cocktail.di

import android.app.Application
import com.example.cocktail.data.repository.CocktailRepositoryImpl
import com.example.cocktail.domain.repository.CocktailRepository
import com.example.cocktail.network.CocktailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCocktailService(): CocktailService {
        return Retrofit.Builder()
            .baseUrl("https://test.com")
            .build()
            .create(CocktailService::class.java)
    }

    @Provides
    @Singleton
    fun provideCocktailRepository(
        cocktailService: CocktailService,
        application: Application
    ): CocktailRepository {
        return CocktailRepositoryImpl(cocktailService, application)
    }
}