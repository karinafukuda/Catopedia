package com.example.gatopedia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _favoriteBreeds = MutableLiveData<List<String>>(emptyList())
    val favoriteBreeds: LiveData<List<String>> = _favoriteBreeds

    // Método para adicionar aos favoritos, criando uma nova lista e atualizando o LiveData
    fun addToFavorites(breedName: String) {
        val currentList = _favoriteBreeds.value?.toMutableList() ?: mutableListOf()
        if (!currentList.contains(breedName)) { // Verifique se já não existe na lista
            currentList.add(breedName)
            _favoriteBreeds.value = currentList
        }
    }
}