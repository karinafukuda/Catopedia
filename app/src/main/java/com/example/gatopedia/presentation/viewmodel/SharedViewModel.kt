package com.example.gatopedia.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.example.gatopedia.data.CatData

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    private val _favoriteBreeds = MutableLiveData<List<String>>(emptyList())
    val favoriteBreeds: LiveData<List<String>> = _favoriteBreeds.distinctUntilChanged()

    private val _selectedBreed = MutableLiveData<CatData.Breed?>()
    val selectedBreed: LiveData<CatData.Breed?> = _selectedBreed

    private val _selectedBreedImageUrl = MutableLiveData<String?>()
    val selectedBreedImageUrl: LiveData<String?> = _selectedBreedImageUrl

    fun setSelectedBreedImageUrl(imageUrl: String?) {
        _selectedBreedImageUrl.value = imageUrl
    }

    fun setSelectedBreed(breed: CatData.Breed?) {
        _selectedBreed.value = breed
    }

    init {
        loadFavoriteBreeds()
    }

    fun addToFavorites(breedName: String) {
        val currentList = _favoriteBreeds.value?.toMutableList() ?: mutableListOf()
        if (breedName !in currentList) {
            currentList.add(breedName)
            _favoriteBreeds.value = currentList
            saveFavoriteBreeds(currentList)
        }
    }

    fun removeFromFavorites(breedName: String) {
        val currentList = _favoriteBreeds.value?.toMutableList() ?: mutableListOf()
        currentList.remove(breedName)
        _favoriteBreeds.value = currentList
        saveFavoriteBreeds(currentList)
    }

    private fun loadFavoriteBreeds() {
        val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val favoriteBreedNames =
            sharedPreferences.getStringSet("favoriteBreedNames", emptySet())?.toList()
                ?: emptyList()
        _favoriteBreeds.value = favoriteBreedNames
    }

    private fun saveFavoriteBreeds(favoriteBreeds: List<String>) {
        val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet("favoriteBreedNames", favoriteBreeds.toSet())
        editor.apply()
    }
}