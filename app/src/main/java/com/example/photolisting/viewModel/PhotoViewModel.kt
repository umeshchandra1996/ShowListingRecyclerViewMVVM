package com.example.photolisting.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photolisting.model.PhotoListingResponces
import com.example.photolisting.repository.PhotoListingRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class PhotoViewModel (val repository: PhotoListingRepository): ViewModel() {
    var liveData:MutableLiveData <List<PhotoListingResponces>> =MutableLiveData()

    init {
        getPhotoList();
        }

    fun getPhotoList() = viewModelScope.launch {
        val response = repository.getPhotoList("1","200")
        liveData.postValue(handlePhotoResponse(response))
    }

    private fun handlePhotoResponse(response: Response<List<PhotoListingResponces>>?): List<PhotoListingResponces>? {
        if(response!!.isSuccessful){
            return response.body()
        }
        else {
            print("errror")
            return null
        }

    }



}