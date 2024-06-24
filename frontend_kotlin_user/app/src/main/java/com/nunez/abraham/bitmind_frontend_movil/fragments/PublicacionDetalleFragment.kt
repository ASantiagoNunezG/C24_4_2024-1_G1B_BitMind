package com.nunez.abraham.bitmind_frontend_movil.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nunez.abraham.bitmind_frontend_movil.R


class PublicacionDetalleFragment : Fragment() {

    //private val args: PublicacionDetalleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Obtener el ID de la publicación desde los argumentos
        //val idPublicacion = args.idPublicacion

        // Aquí puedes usar idPublicacion para cargar los detalles de la publicación desde tu fuente de datos

        return inflater.inflate(R.layout.fragment_publicacion_detalle, container, false)
    }
}