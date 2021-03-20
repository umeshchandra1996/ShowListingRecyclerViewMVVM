package com.example.photolisting.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photolisting.repository.PhotoListingRepository

class viewModelProvider (var repository: PhotoListingRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(repository) as T
    }
}