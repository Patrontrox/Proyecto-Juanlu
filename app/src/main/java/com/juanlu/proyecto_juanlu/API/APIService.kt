package com.juanlu.proyecto_juanlu.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("roms/{romName}")
    suspend fun  getRomImage(@Url url: String): Response<ApiRespuesta>
}