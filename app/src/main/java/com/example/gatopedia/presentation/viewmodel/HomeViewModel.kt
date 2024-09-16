package com.example.gatopedia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatopedia.data.CatData
import com.example.gatopedia.service.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _catImages = MutableLiveData<List<CatData>?>()
    val catImages: LiveData<List<CatData>?> get() = _catImages

    private val _searchImages = MutableLiveData<List<CatData>?>()
    val searchImages: LiveData<List<CatData>?> get() = _searchImages

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchRandomCatList()
    }

    fun fetchRandomCatList() {
        viewModelScope.launch {
            RetrofitClient.catApi.getCatInformation()
                .enqueue(object : Callback<List<CatData>> {
                    override fun onResponse(
                        call: Call<List<CatData>>,
                        response: Response<List<CatData>>
                    ) {
                        _isLoading.postValue(true)
                        if (response.isSuccessful) {
                            response.body()?.let { catImages ->
                                _catImages.postValue(catImages)
                                _isLoading.postValue(false)
                            }
                        } else _error.postValue("$ERROR${response.message()}").also {
                            _isLoading.postValue(false)
                        }
                    }

                    override fun onFailure(call: Call<List<CatData>>, t: Throwable) {
                        _error.postValue("$ERROR_MESSAGE_FAIL${t.message}")
                        _isLoading.postValue(false)
                    }
                })
        }
    }

    fun fetchImagesByBreeds(catId: String) {
        viewModelScope.launch {
            RetrofitClient.catApi.searchImagesByBreed(catId)
                .enqueue(object : Callback<List<CatData>> {
                    override fun onResponse(
                        call: Call<List<CatData>>,
                        response: Response<List<CatData>>
                    ) {
                        _isLoading.postValue(true)
                        if (response.isSuccessful) {
                            val images = response.body()
                            if (images.isNullOrEmpty()) {
                                _error.postValue(BREED_NOT_FOUND)
                                _isLoading.postValue(false)
                            } else {
                                _catImages.postValue(images)
                                _searchImages.postValue(images)
                                _isLoading.postValue(false)
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<CatData>>, t: Throwable) {
                        _error.postValue("$ERROR_NETWORK${t.message}").also {
                            _isLoading.postValue(false)
                        }
                    }
                })
        }
    }

    companion object {
        const val ERROR_MESSAGE_FAIL = "Error fetching images: "
        const val ERROR_NETWORK = "Network error: "
        const val ERROR = "Error: "
        const val BREED_NOT_FOUND = "No images found for this breed"
    }
}