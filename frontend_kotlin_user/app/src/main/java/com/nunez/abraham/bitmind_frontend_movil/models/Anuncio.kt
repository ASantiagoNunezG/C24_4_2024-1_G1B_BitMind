package com.nunez.abraham.bitmind_frontend_movil.models

import java.time.LocalDateTime

data class Anuncio(
    val idAnuncio: Int,
    val titulo: String,
    val descripcion: String,
    val imagen: String,
    val fechaCreacion: LocalDateTime,
    val usuario: Usuario
)
