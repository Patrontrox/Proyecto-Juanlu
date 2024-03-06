package com.juanlu.proyecto_juanlu.API

import com.google.gson.annotations.SerializedName

data class ApiRespuesta(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("foto") val foto: List<String>,
    @SerializedName("url") val url: String
)