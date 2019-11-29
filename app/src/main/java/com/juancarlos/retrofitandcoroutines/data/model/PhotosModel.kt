package com.juancarlos.retrofitandcoroutines.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PhotosModel (
    @Json(name="albumId")
    var albumId: Int,
    @Json(name="id")
    var id: Int,
    @Json(name="title")
    val title: String,
    @Json(name="url")
    val url: String,
    @Json(name="thumbnailUrl")
    val thumbnailUrl: String,
    @Json(name= "certification")
    val certification: List<CertificationModel>
) {
}
data class CertificationModel(
    @Json(name="id")
    val id: String,
    @Json(name= "id_course")
    val id_course: String,
    @Json(name="name")
    val name: String,
    @Json(name="end_date")
    val end_date: String,
    @Json(name="start_date")
    val start_date: String,
    @Json(name="enterprise")
    val enterprise: List<EnterpriseModel>
)

data class EnterpriseModel(
    @Json(name="name")
    val name: String
)