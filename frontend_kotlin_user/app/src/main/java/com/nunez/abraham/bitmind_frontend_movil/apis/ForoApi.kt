package com.nunez.abraham.bitmind_frontend_movil.apis

import com.nunez.abraham.bitmind_frontend_movil.models.Foro
import retrofit2.http.GET

interface ForoApi {
    @GET("forosM")
    suspend fun getForos(): List<Foro>
}