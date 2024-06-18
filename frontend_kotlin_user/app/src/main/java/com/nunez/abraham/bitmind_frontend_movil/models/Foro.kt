package com.nunez.abraham.bitmind_frontend_movil.models

import java.time.LocalDateTime

data class Foro(
    val idForo: Int,
    val titulo: String,
    val contenido: String,
    val fechaCreacion: LocalDateTime,
    val usuario: Usuario,
    val comentarios: List<Comentario>
)
