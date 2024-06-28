package com.nunez.abraham.bitmind_frontend_movil.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nunez.abraham.bitmind_frontend_movil.R
import com.nunez.abraham.bitmind_frontend_movil.models.Anuncio
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AnuncioAdapter (private  val anuncios: List<Anuncio>): RecyclerView.Adapter<AnuncioAdapter.AnuncioViewHolder>(){


    @SuppressLint("ClickableViewAccessibility")
    class AnuncioViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val titulo:TextView = itemView.findViewById(R.id.txttitulo)
        val fechaCreacion: TextView = itemView.findViewById(R.id.fechaCreacion)
        val descripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val horaCreacion: TextView = itemView.findViewById(R.id.horaCreacion)
        val usuario: TextView = itemView.findViewById(R.id.txtUsuario)
        val imagenA: ImageView = itemView.findViewById(R.id.imagenAnuncio)
        val scrollView: ScrollView = itemView.findViewById(R.id.scrollView)

        init {
            scrollView.setOnClickListener {
                // Al detectar un long press en el ScrollView, desactivar la intercepción de eventos táctiles
                it.parent.requestDisallowInterceptTouchEvent(true)
                false // Permitir que el long press se propague
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anuncio,parent,false)
        return AnuncioViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AnuncioViewHolder, position: Int) {
        val anuncio = anuncios[position]
        holder.titulo.text = anuncio.titulo
        holder.usuario.text = "Usuario: ${anuncio.usuario.nombres}"
        holder.fechaCreacion.text = formatDateFecha(anuncio.fechaCreacion)
        holder.horaCreacion.text = formatDateHora(anuncio.fechaCreacion)
        holder.descripcion.text = anuncio.descripcion

        // Cargar la imagen utilizando Glide
        Glide.with(holder.itemView)
            .load(anuncio.imagen)
            .placeholder(R.drawable.cargando)
            .error(R.drawable.img_default)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(holder.imagenA)

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

    override fun getItemCount() = anuncios.size
}