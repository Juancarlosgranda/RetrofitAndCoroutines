package com.juancarlos.retrofitandcoroutines.data

import android.util.Log
import com.juancarlos.retrofitandcoroutines.data.model.PhotosModel
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

class PhotosRepository(val dataSource: PhotosDataSource) {

    private var photoList: List<PhotosModel>? = null

    suspend fun getPhotosList(): List<PhotosModel>? {
        // handle login
        val response = dataSource.getPhotos()
        Log.d("cuack","result: ${response.body()}")
        if (response.isSuccessful) {
            photoList = response.body()
        } else {
            return null
        }

        return photoList
    }

}