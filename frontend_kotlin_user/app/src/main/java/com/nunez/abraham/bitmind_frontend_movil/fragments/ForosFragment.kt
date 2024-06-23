package com.nunez.abraham.bitmind_frontend_movil.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunez.abraham.bitmind_frontend_movil.R
import com.nunez.abraham.bitmind_frontend_movil.adapters.ForoAdapter
import com.nunez.abraham.bitmind_frontend_movil.databinding.FragmentForosBinding
import com.nunez.abraham.bitmind_frontend_movil.models.Foro
import com.nunez.abraham.bitmind_frontend_movil.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForosFragment : Fragment() {

    private var _binding: FragmentForosBinding? = null
    private val binding get() = _binding!!
    private lateinit var foroAdapter: ForoAdapter
    private val foros = mutableListOf<Foro>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentForosBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewForos.layoutManager = LinearLayoutManager(requireContext())
        foroAdapter = ForoAdapter(foros)
        binding.recyclerViewForos.adapter = foroAdapter
        loadForos()
    }

    private fun loadForos(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.foroApi.getForos()
                withContext(Dispatchers.Main){
                    foros.clear()
                    foros.addAll(response)
                    foroAdapter.notifyDataSetChanged()
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}