package com.juancarlos.retrofitandcoroutines.data.model

import com.google.gson.annotations.SerializedName

data class PhotosModel (
    @SerializedName("albumId")
    var albumId: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
) {
}