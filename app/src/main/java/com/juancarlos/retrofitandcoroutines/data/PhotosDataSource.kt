package com.juancarlos.retrofitandcoroutines.data

import com.juancarlos.retrofitandcoroutines.data.model.PhotosModel
import com.juancarlos.retrofitandcoroutines.data.network.PhotosService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PhotosDataSource {
    suspend fun getPhotos(): Response<List<PhotosModel>> {
            return PhotosService.makePhotosRetrofitService().getPhotos()
    }
}