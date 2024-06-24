package com.nunez.abraham.bitmind_frontend_movil.adapters

import android.os.Build
import android.util.Log
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
import com.nunez.abraham.bitmind_frontend_movil.fragments.PublicacionesFragment
import com.nunez.abraham.bitmind_frontend_movil.fragments.PublicacionesFragmentDirections
import com.nunez.abraham.bitmind_frontend_movil.models.Publicacion
import com.nunez.abraham.bitmind_frontend_movil.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PublicacionAdapter(private val publicaciones: List<Publicacion>) : RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder>() {

    inner class PublicacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private lateinit var publicacion: Publicacion

        init {
            val btn: Button = itemView.findViewById(R.id.btnNavigate_to_Publication_detail)
            btn.setOnClickListener {
                val currentPublicacion = publicaciones[adapterPosition]
                // Llamar al método para incrementar vistas usando Retrofit
                incrementarVistas(currentPublicacion.idPublicacion)

                itemView.findNavController().navigate(R.id.action_publicacionesFragment_to_publicacionDetalleFragment)
                // Navegar a la pantalla de detalle y pasar el argumento 'idPublicacion'
                //val action = PublicacionesFragmentDirections.actionPublicacionesFragmentToPublicacionDetalleFragment(idPublicacion = currentPublicacion.idPublicacion)
                //itemView.findNavController().navigate(action)
            }
        }


        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val fechaCreacion: TextView = itemView.findViewById(R.id.fechaCreacion)
        val valoracion: TextView = itemView.findViewById(R.id.valoracion)
        val horaCreacion: TextView = itemView.findViewById(R.id.horaCreacion)
        val imagen: ImageView = itemView.findViewById(R.id.imagenPublicacion)
        val vistas: TextView = itemView.findViewById(R.id.vistas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicacionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_publicacion, parent, false)
        return PublicacionViewHolder(view)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PublicacionViewHolder, position: Int) {
        val publicacion = publicaciones[position]
        holder.titulo.text = publicacion.titulo
        holder.fechaCreacion.text = formatDateFecha(publicacion.fechaCreacion)
        holder.horaCreacion.text = formatDateHora(publicacion.fechaCreacion)
        holder.valoracion.text = "${publicacion.promedioValoracion.toString()}/10"
        holder.vistas.text = publicacion.vistas

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

    // Método para llamar a Retrofit y incrementar las vistas
    private fun incrementarVistas(idPublicacion: Int) {
        // Llamar al método de Retrofit para incrementar vistas
        GlobalScope.launch(Dispatchers.IO) {
            try {
                RetrofitInstance.publicacionApi.incrementarVista(idPublicacion)
                // Actualizar las vistas localmente si es necesario
                // Por ejemplo, puedes incrementar la variable en el objeto publicacion
                // publicacion.vistas++
            } catch (e: Exception) {
                // Manejar errores de manera apropiada
                Log.e("PublicacionAdapter", "Error al incrementar vistas: ${e.message}")
            }
        }
    }

    override fun getItemCount() = publicaciones.size
}