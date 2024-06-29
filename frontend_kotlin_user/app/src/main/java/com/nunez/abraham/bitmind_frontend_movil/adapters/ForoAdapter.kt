package com.nunez.abraham.bitmind_frontend_movil.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nunez.abraham.bitmind_frontend_movil.R
import com.nunez.abraham.bitmind_frontend_movil.models.Foro
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ForoAdapter(private val context: Context, private val foros: List<Foro>) : RecyclerView.Adapter<ForoAdapter.ForoViewHolder>() {

    class ForoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTituloF)
        val horaCreacion: TextView = itemView.findViewById(R.id.horaCreacion)
        val fechaCreacion: TextView = itemView.findViewById(R.id.fechaCreacion)
        val usuario: TextView = itemView.findViewById(R.id.txtUsuarioF)
        val imagenU: ImageView = itemView.findViewById(R.id.imgPerfilF)
        val btn: Button = itemView.findViewById(R.id.btnForoVer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_foro, parent, false)
        return ForoViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ForoViewHolder, position: Int) {
        val foro = foros[position]
        holder.titulo.text = foro.titulo
        holder.fechaCreacion.text = formatDateFecha(foro.fechaCreacion)
        holder.horaCreacion.text = formatDateHora(foro.fechaCreacion)
        holder.usuario.text = foro.usuario.nombres

        // Cargar la imagen utilizando Glide
        Glide.with(holder.itemView)
            .load(foro.usuario.imagen)
            .placeholder(R.drawable.cargando)
            .error(R.drawable.icons8_usuario)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerInside()
            .into(holder.imagenU)

        holder.btn.setOnClickListener {
            Toast.makeText(context, "Ingrese a la aplicación Web para poder unirse y participar en el foro", Toast.LENGTH_LONG).show()
        }
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

    override fun getItemCount() = foros.size
}
