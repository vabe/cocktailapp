package com.example.cocktail.ui.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail.domain.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    cocktailRepository: CocktailRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val cocktails = cocktailRepository.getCocktails()
            Log.d(
                "TEST - All gins",
                cocktails.toString()
            )
            Log.d(
                "TEST - Negroni",
                cocktailRepository.getCocktailById("11007").toString()
            )
            Log.d(
                "TEST - Cocktails by name",
                cocktailRepository.findCocktailsByIngredient("Gin").toString()
            )
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text
}