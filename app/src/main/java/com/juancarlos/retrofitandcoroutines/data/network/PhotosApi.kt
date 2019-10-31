package com.juancarlos.retrofitandcoroutines.data.network

import com.juancarlos.retrofitandcoroutines.data.model.PhotosModel
import retrofit2.Response
import retrofit2.http.GET

interface PhotosApi {
    @GET("photos")
    suspend fun getPhotos(): Response<List<PhotosModel>>
}