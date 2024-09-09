package com.example.gatopedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatopedia.model.CatInfo
import com.example.gatopedia.service.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _catImages = MutableLiveData<List<CatInfo>>()
    val catImages: LiveData<List<CatInfo>> get() = _catImages

    private val _breeds = MutableLiveData<List<CatInfo.Breed>>()
    val breeds: LiveData<List<CatInfo.Breed>> get() = _breeds

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchCatList()
    }

    fun fetchCatList() {
        viewModelScope.launch {
            RetrofitClient.catApi.getCatInformation().enqueue(object : Callback<List<CatInfo>> {
                override fun onResponse(call: Call<List<CatInfo>>, response: Response<List<CatInfo>>) {
                    if (response.isSuccessful) {
                        response.body()?.let { catImages ->
                            _catImages.postValue(catImages)
                        }
                    } else _error.postValue("$ERROR${response.message()}")
                }
                override fun onFailure(call: Call<List<CatInfo>>, t: Throwable) {
                    _error.postValue("$ERROR_MESSAGE_FAIL${t.message}")
                }
            })
        }
    }

    fun searchBreedsByName(query: String) {
        viewModelScope.launch {
            RetrofitClient.catApi.searchBreedsByName(query).enqueue(object : Callback<List<CatInfo.Breed>> {
                override fun onResponse(call: Call<List<CatInfo.Breed>>, response: Response<List<CatInfo.Breed>>) {
                    if (response.isSuccessful) {
                        _breeds.postValue(response.body())
                    } else {
                        _error.postValue("Erro ao buscar raças por nome: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<CatInfo.Breed>>, t: Throwable) {
                    _error.postValue("Erro ao buscar raças por nome: ${t.message}")
                }
            })
        }
    }

    companion object {
        const val ERROR_MESSAGE_FAIL = "Erro ao buscar imagens: "
        const val ERROR = "Erro: "
        const val ERROR_NOT_FOUND = "Não encontramos o gatinho."
        const val LIMIT_SEARCH_DEFAULT = 10
    }

}