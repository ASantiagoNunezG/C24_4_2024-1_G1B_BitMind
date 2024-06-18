package com.nunez.abraham.bitmind_frontend_movil.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.nunez.abraham.bitmind_frontend_movil.R
import com.nunez.abraham.bitmind_frontend_movil.models.Publicacion
import com.nunez.abraham.bitmind_frontend_movil.models.Usuario

class PublicacionAdapter(private val publicaciones: List<Publicacion>) : RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder>() {

    class PublicacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val descripcion: TextView = itemView.findViewById(R.id.descripcion)
        val fechaCreacion: TextView = itemView.findViewById(R.id.fechaCreacion)
        val fechaModificacion: TextView = itemView.findViewById(R.id.fechaModificacion)
        val usuarioNombres: TextView = itemView.findViewById(R.id.usuarioNombres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicacionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_publicacion, parent, false)
        return PublicacionViewHolder(view)
    }


    override fun onBindViewHolder(holder: PublicacionViewHolder, position: Int) {
        val publicacion = publicaciones[position]
        holder.titulo.text = publicacion.titulo
        holder.descripcion.text = publicacion.descripcion
        holder.fechaCreacion.text = publicacion.fechaCreacion.toString()
        holder.fechaModificacion.text = publicacion.fechaModificacion.toString()
        holder.usuarioNombres.text = "Usuario: ${publicacion.usuario.correo}"
    }

    override fun getItemCount() = publicaciones.size
}