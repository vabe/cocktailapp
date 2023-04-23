package com.example.cocktail.ui.details

import androidx.lifecycle.ViewModel
import com.example.cocktail.domain.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
): ViewModel() {
//    private val cocktailId: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)
//    val cocktailDetailsFlow = cocktailId.flatMapLatest {
//        cocktailRepository.getCocktailById(it)
//    }
//
//    fun loadCocktailById(id: Long) = cocktailId.tryEmit(id)
    fun loadCocktailById(id: Long) = null
}