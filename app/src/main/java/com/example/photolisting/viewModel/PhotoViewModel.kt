package com.example.photolisting.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photolisting.model.PhotoListingDataResponseItem
import com.example.photolisting.repository.PhotoListingRepository
import com.example.photolisting.resource.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception


class PhotoViewModel (val repository: PhotoListingRepository): ViewModel() {
   private var liveData:MutableLiveData<Resource<Response<List<PhotoListingDataResponseItem>>>> =MutableLiveData()
    var _liveData:LiveData<Resource<Response<List<PhotoListingDataResponseItem>>>> = liveData

    init {
        getPhotoList();
        }

    fun getPhotoList() = viewModelScope.launch {
        viewModelScope.launch {
            try {
                val response = repository.getPhotoList("1","200")
                liveData.postValue(Resource.success(response))

            }
            catch (e:Exception){
//                Log.e("PhotoViewModel", e.message.toString());
                liveData.postValue(Resource.error(null,e.message.toString()))

            }

        }
    }




}