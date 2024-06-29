package com.nunez.abraham.bitmind_frontend_movil.adapters

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nunez.abraham.bitmind_frontend_movil.R
import com.nunez.abraham.bitmind_frontend_movil.models.Publicacion
import com.nunez.abraham.bitmind_frontend_movil.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PublicacionAdapter(private val publicaciones: List<Publicacion>) : RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder>() {

    inner class PublicacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val fechaCreacion: TextView = itemView.findViewById(R.id.fechaCreacion)
        val valoracion: TextView = itemView.findViewById(R.id.valoracion)
        val horaCreacion: TextView = itemView.findViewById(R.id.horaCreacion)
        val imagen: ImageView = itemView.findViewById(R.id.imagenPublicacion)
        val vistas: TextView = itemView.findViewById(R.id.vistas)
        val recyclerViewArchivos: RecyclerView = itemView.findViewById(R.id.recyclerViewArchivos)
        val btnVerMas: Button = itemView.findViewById(R.id.btnNavigate_to_Publication_detail)
        val descripcion: TextView = itemView.findViewById(R.id.puDescripcion)
        val carrera: TextView = itemView.findViewById(R.id.carrera)
        val ciclo: TextView = itemView.findViewById(R.id.ciclo)
        val curso: TextView = itemView.findViewById(R.id.curso)
        init {
            btnVerMas.setOnClickListener {
                val currentPublicacion = publicaciones[adapterPosition]
                incrementarVistas(currentPublicacion.idPublicacion)
                Toast.makeText(itemView.context, "Para mas información ingrese a la aplicación Web BitMind", Toast.LENGTH_LONG).show()
            }
        }
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
        holder.valoracion.text = "${publicacion.promedioValoracion}/10"
        holder.vistas.text = publicacion.vistas
        holder.carrera.text = "Carrera: ${publicacion.carrera.nombre}"
        holder.ciclo.text = "Ciclo: ${publicacion.ciclo.nombre}"
        holder.curso.text = "Curso: ${publicacion.curso.nombre}"
        holder.descripcion.text = publicacion.descripcion

        Glide.with(holder.itemView)
            .load(publicacion.imagen)
            .placeholder(R.drawable.cargando)
            .error(R.drawable.img_default)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(holder.imagen)

        holder.recyclerViewArchivos.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)
        holder.recyclerViewArchivos.adapter = ArchivoAdapter(publicacion.archivos)
    }

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

    private fun incrementarVistas(idPublicacion: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                RetrofitInstance.publicacionApi.incrementarVista(idPublicacion)
            } catch (e: Exception) {
                Log.e("PublicacionAdapter", "Error al incrementar vistas: ${e.message}")
            }
        }
    }

    override fun getItemCount() = publicaciones.size
}
