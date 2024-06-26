package com.nunez.abraham.bitmind_frontend_movil.apis

import com.nunez.abraham.bitmind_frontend_movil.models.Anuncio
import retrofit2.http.GET

interface AnuncioApi {
    @GET("anunciosM")
    suspend fun getAnuncios(): List<Anuncio>
}