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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun searchCatImages(limit: Int = 3, breedIds: String? = null, page: Int = 0) {
        viewModelScope.launch {
            RetrofitClient.catApi.searchImages(limit, breedIds, page).enqueue(object : Callback<List<CatInfo>> {
                override fun onResponse(call: Call<List<CatInfo>>, response: Response<List<CatInfo>>) {
                    if (response.isSuccessful) {
                        response.body()?.let { catImages ->
                            _catImages.postValue(catImages)
                        }
                    } else {
                        _error.postValue("Erro: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<CatInfo>>, t: Throwable) {
                    _error.postValue("Erro ao buscar imagens: ${t.message}")
                }
            })
        }
    }

}