package com.nunez.abraham.bitmind_frontend_movil.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nunez.abraham.bitmind_frontend_movil.R
import com.nunez.abraham.bitmind_frontend_movil.models.Archivo

class ArchivoAdapter(private val archivos: List<Archivo>) : RecyclerView.Adapter<ArchivoAdapter.ArchivoViewHolder>() {

    class ArchivoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreArchivo: TextView = itemView.findViewById(R.id.nombreArchivo)
        val urlarchivo: TextView = itemView.findViewById(R.id.urlArchivo)
        val tipoarchivo: TextView = itemView.findViewById(R.id.tipoArchivo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchivoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_archivo, parent, false)
        return ArchivoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArchivoViewHolder, position: Int) {
        val archivo = archivos[position]
        holder.nombreArchivo.text = archivo.nombre
        holder.urlarchivo.text = "Url: ${archivo.url}"
        holder.tipoarchivo.text = "Tipo de archivo: ${archivo.tipo}"

        // Hacer clicable la URL
        holder.urlarchivo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(archivo.url))
            holder.itemView.context.startActivity(intent)
        }

        // Agregar opción para copiar la URL al portapapeles
        holder.urlarchivo.setOnLongClickListener {
            val clipboard = holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("URL", archivo.url)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(holder.itemView.context, "URL copiada al portapapeles", Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun getItemCount() = archivos.size
}
