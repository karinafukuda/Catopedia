package com.example.gatopedia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gatopedia.data.CatData

class SharedViewModel: ViewModel() {

    // LiveData para armazenar a lista de favoritos
    private val _favoriteBreeds = MutableLiveData<List<String>>(emptyList())
    val favoriteBreeds: LiveData<List<String>> = _favoriteBreeds

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

    // Método para adicionar aos favoritos, criando uma nova lista e atualizando o LiveData
    fun addToFavorites(breedName: String) {
        val currentList = _favoriteBreeds.value?.toMutableList() ?: mutableListOf()
        if (!currentList.contains(breedName)) { // Verifica se já não existe na lista
            currentList.add(breedName)
            _favoriteBreeds.value = currentList
        }
    }
}