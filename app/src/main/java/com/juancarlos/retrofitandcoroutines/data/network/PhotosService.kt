package com.juancarlos.retrofitandcoroutines.data.network

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PhotosService {
    const val BASE_URL = "https://my-json-server.typicode.com/"

    fun makePhotosRetrofitService(): PhotosApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient().asLenient())
            .build().create(PhotosApi::class.java)
    }
}
