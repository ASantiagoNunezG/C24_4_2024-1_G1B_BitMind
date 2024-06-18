package com.nunez.abraham.bitmind_frontend_movil.models

data class Archivo(
    val idArchivo: Int,
    val nombre: String,
    val url: String,
    val tipo: String,
    val publicacion: Publicacion
)
