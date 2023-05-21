package com.example.cocktail.ui.details


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail.domain.repository.CocktailRepository
import com.example.cocktail.model.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    cocktailRepository: CocktailRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _cocktail = MutableLiveData<Cocktail>()
    val cocktail : LiveData<Cocktail>
        get () = _cocktail

    init {
        viewModelScope.launch {
            val cocktailId = savedStateHandle.get<String>("cocktailId").toString()

            _cocktail.value = cocktailRepository.getCocktailById(cocktailId)
        }
    }
}