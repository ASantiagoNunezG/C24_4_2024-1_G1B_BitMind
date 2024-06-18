package com.nunez.abraham.bitmind_frontend_movil.models

import java.time.LocalDateTime

data class Valoracion(
    val idValoracion: Int,
    val escala: Int,
    val fechaCreacion: LocalDateTime,
    val usuario: Usuario,
    val publicacion: Publicacion
)
