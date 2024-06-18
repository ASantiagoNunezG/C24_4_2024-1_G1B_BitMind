package com.nunez.abraham.bitmind_frontend_movil.models

import java.time.LocalDateTime

data class Comentario(
    val idComentario: Int,
    val contenido: String,
    val fechaCreacion: LocalDateTime,
    val usuario: Usuario,
    val foro: Foro
)
