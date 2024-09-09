package com.example.gatopedia.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatopedia.data.CatInformation
import com.example.gatopedia.service.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _catImages = MutableLiveData<List<CatInformation>>()
    val catImages: LiveData<List<CatInformation>> get() = _catImages

    private val _breeds = MutableLiveData<List<CatInformation.Breed>>()
    val breeds: LiveData<List<CatInformation.Breed>> get() = _breeds

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchCatList()
        searchBreedsByName("")
    }

    fun fetchCatList() {
        viewModelScope.launch {
            RetrofitClient.catApiService.getCatInformation().enqueue(object : Callback<List<CatInformation>> {
                override fun onResponse(call: Call<List<CatInformation>>, response: Response<List<CatInformation>>) {
                    if (response.isSuccessful) {
                        response.body()?.let { catImages ->
                            _catImages.postValue(catImages)
                        }
                    } else _error.postValue("$ERROR${response.message()}")
                }
                override fun onFailure(call: Call<List<CatInformation>>, t: Throwable) {
                    _error.postValue("$ERROR_MESSAGE_FAIL${t.message}")
                }
            })
        }
    }

    fun searchBreedsByName(query: String) {
        viewModelScope.launch {
            RetrofitClient.catApiService.searchBreedsByName(query).enqueue(object : Callback<List<CatInformation.Breed>> {
                override fun onResponse(call: Call<List<CatInformation.Breed>>, response: Response<List<CatInformation.Breed>>) {
                    if (response.isSuccessful) {
                        _breeds.postValue(response.body())
                    } else {
                        _error.postValue("$ERROR_NOT_FOUND${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<CatInformation.Breed>>, t: Throwable) {
                    _error.postValue("$ERROR_NOT_FOUND${t.message}")
                }
            })
        }
    }

    fun fetchBreedDetails(catId: String) {
        viewModelScope.launch {
            // Faça a chamada à API para buscar os detalhes da raça com base no catId
            // Atualize uma LiveData com os detalhes da raça para que o Fragment possa observá-la e atualizar a interface
            RetrofitClient.catApiService.searchBreedsByName(catId).enqueue(object : Callback<List<CatInformation.Breed>> {
                override fun onResponse(call: Call<List<CatInformation.Breed>>, response: Response<List<CatInformation.Breed>>) {
                    if (response.isSuccessful) {
                        _breeds.postValue(response.body())
                    } else {
                        _error.postValue("$ERROR_NOT_FOUND${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<CatInformation.Breed>>, t: Throwable) {
                    _error.postValue("$ERROR_NOT_FOUND${t.message}")
                }
            })
        }
    }

    companion object {
        const val ERROR_MESSAGE_FAIL = "Erro ao buscar imagens: "
        const val ERROR = "Erro: "
        const val ERROR_NOT_FOUND = "Erro ao buscar raças por nome: "
        const val LIMIT_SEARCH_DEFAULT = 10
    }

}