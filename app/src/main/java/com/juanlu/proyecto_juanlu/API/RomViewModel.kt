package com.juanlu.proyecto_juanlu.API

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RomViewModel : ViewModel() {
    private val baseUrl = "http://tu-servidor.com/" // Reemplaza con la URL de tu servidor
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun getRomImage(url: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<ApiRespuesta> = apiService.getRomImage(url)
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.foto?.get(0)
                } else {
                    null
                }
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
}


