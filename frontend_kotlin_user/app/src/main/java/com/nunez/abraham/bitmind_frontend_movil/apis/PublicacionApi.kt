package com.nunez.abraham.bitmind_frontend_movil.apis

import com.nunez.abraham.bitmind_frontend_movil.models.Publicacion
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PublicacionApi {
    @GET("publicacionesM")
    suspend fun getPublicaciones(): List<Publicacion>

    @POST("publicacionesM/{id}/addview")
    suspend fun incrementarVista(@Path("id") id: Int)

    @GET("publicacionesM/{id}")
    suspend fun getPublicacion(@Path("id") id: Int)

}