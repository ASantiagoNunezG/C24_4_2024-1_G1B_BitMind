package com.nunez.abraham.bitmind_frontend_movil.apis

import com.nunez.abraham.bitmind_frontend_movil.models.Publicacion
import retrofit2.http.GET

interface PublicacionApi {
    @GET("publicaciones")
    suspend fun getPublicaciones(): List<Publicacion>
}