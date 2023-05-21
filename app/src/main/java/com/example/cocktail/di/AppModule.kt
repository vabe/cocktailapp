package com.example.cocktail.di

import android.app.Application
import androidx.room.Room
import com.example.cocktail.data.repository.CocktailRepositoryImpl
import com.example.cocktail.domain.repository.CocktailRepository
import com.example.cocktail.network.CocktailService
import com.example.cocktail.persistence.AppDatabase
import com.example.cocktail.persistence.CocktailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCocktailService(): CocktailService {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "cocktails").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCocktailDao(appDatabase: AppDatabase): CocktailDao {
        return appDatabase.cocktailDao()
    }

    @Provides
    @Singleton
    fun provideCocktailRepository(
        cocktailService: CocktailService,
        cocktailDao: CocktailDao
    ): CocktailRepository {
        return CocktailRepositoryImpl(cocktailService, cocktailDao)
    }
}