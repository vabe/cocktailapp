package com.example.cocktail.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail.domain.repository.CocktailRepository
import com.example.cocktail.model.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {

    private val _cocktails = MutableLiveData<List<Cocktail>>()
    val cocktails : LiveData<List<Cocktail>>
        get () = _cocktails

    init {
        viewModelScope.launch {
            val cocktails = cocktailRepository.getCocktails()
            _cocktails.value = cocktails
        }
    }

    fun updateCocktailsByIngredient(ingredient: String) {
        viewModelScope.launch {
            val cocktails = cocktailRepository.findCocktailsByIngredient(ingredient)
            _cocktails.value = cocktails
        }
    }

    fun revertCocktailsToDefault() {
        viewModelScope.launch {
            val cocktails = cocktailRepository.getCocktails()
            _cocktails.value = cocktails
        }
    }
}