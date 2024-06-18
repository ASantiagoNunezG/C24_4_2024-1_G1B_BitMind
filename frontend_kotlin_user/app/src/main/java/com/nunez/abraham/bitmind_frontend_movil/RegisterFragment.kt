package com.nunez.abraham.bitmind_frontend_movil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nunez.abraham.bitmind_frontend_movil.databinding.FragmentLoginBinding
import com.nunez.abraham.bitmind_frontend_movil.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linkInicio.setOnClickListener {
            // Reemplazar el fragmento al hacer clic en el botón
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, LoginFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        //Boton funcionalidad Para ingresar al Home
        binding.btnRegistro.setOnClickListener{
            startActivity(Intent(context,HomeActivity::class.java))
        }
    }


}