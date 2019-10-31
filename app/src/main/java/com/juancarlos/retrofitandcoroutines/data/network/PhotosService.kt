package com.juancarlos.retrofitandcoroutines.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PhotosService {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun makePhotosRetrofitService(): PhotosApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(PhotosApi::class.java)
    }
}
