package com.nunez.abraham.bitmind_frontend_movil.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunez.abraham.bitmind_frontend_movil.adapters.PublicacionAdapter
import com.nunez.abraham.bitmind_frontend_movil.databinding.FragmentPublicacionesBinding
import com.nunez.abraham.bitmind_frontend_movil.models.Publicacion
import com.nunez.abraham.bitmind_frontend_movil.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PublicacionesFragment : Fragment() {

    private var _binding: FragmentPublicacionesBinding? = null
    private val binding get() = _binding!!
    private lateinit var publicacionAdapter: PublicacionAdapter
    private val publicaciones = mutableListOf<Publicacion>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublicacionesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewPublicaciones.layoutManager = LinearLayoutManager(requireContext())
        publicacionAdapter = PublicacionAdapter(publicaciones)
        binding.recyclerViewPublicaciones.adapter = publicacionAdapter

        loadPublicaciones()
    }


    private fun loadPublicaciones() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.publicacionApi.getPublicaciones()
                withContext(Dispatchers.Main) {
                    publicaciones.clear()
                    publicaciones.addAll(response)
                    publicacionAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}