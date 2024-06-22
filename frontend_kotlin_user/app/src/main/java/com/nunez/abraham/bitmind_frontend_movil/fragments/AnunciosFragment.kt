package com.nunez.abraham.bitmind_frontend_movil.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunez.abraham.bitmind_frontend_movil.adapters.AnuncioAdapter
import com.nunez.abraham.bitmind_frontend_movil.databinding.FragmentAnunciosBinding
import com.nunez.abraham.bitmind_frontend_movil.models.Anuncio
import com.nunez.abraham.bitmind_frontend_movil.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AnunciosFragment : Fragment() {

    private var _binding: FragmentAnunciosBinding? = null
    private val binding get() = _binding!!
    private lateinit var anuncioAdapter: AnuncioAdapter
    private val anuncios = mutableListOf<Anuncio>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnunciosBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Configurar el RecyclerView con un LinearLayoutManager en lugar de GridLayoutManager
        binding.recyclerViewAnuncios.layoutManager = LinearLayoutManager(requireContext())
        // Inicializa el anuncioAdapter antes de asignarlo al RecyclerView
        anuncioAdapter = AnuncioAdapter(anuncios)
        binding.recyclerViewAnuncios.adapter = anuncioAdapter
        loadAnuncios()
    }
    private fun loadAnuncios(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.anuncioApi.getAnuncios()
                withContext(Dispatchers.Main) {
                    anuncios.clear()
                    anuncios.addAll(response)
                    anuncioAdapter.notifyDataSetChanged()
                }
            }catch(e: Exception){
                e.printStackTrace()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}