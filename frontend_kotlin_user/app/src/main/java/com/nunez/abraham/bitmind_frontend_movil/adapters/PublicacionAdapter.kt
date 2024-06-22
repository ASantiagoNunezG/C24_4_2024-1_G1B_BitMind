package com.nunez.abraham.bitmind_frontend_movil.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nunez.abraham.bitmind_frontend_movil.R
import com.nunez.abraham.bitmind_frontend_movil.models.Publicacion
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PublicacionAdapter(private val publicaciones: List<Publicacion>) : RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder>() {

    class PublicacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        init {
            val btn: Button = itemView.findViewById(R.id.btnNavigate_to_Publication_detail)
            btn.setOnClickListener {
                itemView.findNavController().navigate(R.id.action_publicacionesFragment_to_publicacionDetalleFragment)
            }
        }
        /*
        init {
            val btn: Button = itemView.findViewById(R.id.btnNavigate_to_Publication_detail)
            btn.setOnClickListener {
                val action = PublicacionesFragmentDirections.actionPublicacionesFragmentToPublicacionDetalleFragment()
                itemView.findNavController().navigate(action)
            }
        }*/

        val titulo: TextView = itemView.findViewById(R.id.titulo)
        //val descripcion: TextView = itemView.findViewById(R.id.descripcion)
        val fechaCreacion: TextView = itemView.findViewById(R.id.fechaCreacion)
        val horaCreacion: TextView = itemView.findViewById(R.id.horaCreacion)
        val imagen: ImageView = itemView.findViewById(R.id.imagenPublicacion)
        //val fechaModificacion: TextView = itemView.findViewById(R.id.fechaModificacion)
        //val usuarioNombres: TextView = itemView.findViewById(R.id.usuarioNombres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicacionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_publicacion, parent, false)
        return PublicacionViewHolder(view)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PublicacionViewHolder, position: Int) {
        val publicacion = publicaciones[position]
        holder.titulo.text = publicacion.titulo
        //holder.descripcion.text = publicacion.descripcion
        //val urlImage = publicacion.imagen
        holder.fechaCreacion.text = formatDateFecha(publicacion.fechaCreacion)
        holder.horaCreacion.text = formatDateHora(publicacion.fechaCreacion)
        //holder.fechaModificacion.text = publicacion.fechaModificacion.toString()
        //holder.usuarioNombres.text = "Usuario: ${publicacion.usuario.nombres}"


        // Cargar la imagen utilizando Glide
        Glide.with(holder.itemView)
            .load(publicacion.imagen)
            .placeholder(R.drawable.cargando) // Placeholder mientras se carga la imagen
            .error(R.drawable.img_default) // Imagen de fallback en caso de error
            .transition(DrawableTransitionOptions.withCrossFade()) // Animación de transición
            .centerCrop() // escalará la imagen con corte, no encontre una que no lo haga
            .into(holder.imagen) // Cargar la imagen en el ImageView

    }
    // Función para formatear la hora y la fecha
    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDateHora(fecha: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return fecha.format(formatter)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDateFecha(fecha: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return fecha.format(formatter)
    }

    override fun getItemCount() = publicaciones.size
}