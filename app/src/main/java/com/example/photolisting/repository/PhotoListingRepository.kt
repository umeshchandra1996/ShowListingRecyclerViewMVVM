package com.example.photolisting.repository

import com.example.photolisting.network.ApiClient


class PhotoListingRepository {
    var apiClient: ApiClient= ApiClient()
    suspend fun  getPhotoList(pageNum:String, LimitItem:String)= apiClient.api.getPhotoList(pageNum,LimitItem/*"1","200"*/)

}