package com.example.photolisting.network

import com.example.photolisting.model.PhotoListingResponces
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {
    @GET("list")
    suspend fun getPhotoList(
        @Query("page") pageNum: String?,
        @Query("limit") limitItem: String?
    ): Response <List<PhotoListingResponces>>?
}