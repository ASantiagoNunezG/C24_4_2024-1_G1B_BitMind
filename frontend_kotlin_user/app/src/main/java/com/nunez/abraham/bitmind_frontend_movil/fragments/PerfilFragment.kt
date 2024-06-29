package com.nunez.abraham.bitmind_frontend_movil.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import com.nunez.abraham.bitmind_frontend_movil.IA.ChatRequest
import com.nunez.abraham.bitmind_frontend_movil.IA.ChatResponse
import com.nunez.abraham.bitmind_frontend_movil.IA.Message
import com.nunez.abraham.bitmind_frontend_movil.IA.RetrofitInstanceIA
import com.nunez.abraham.bitmind_frontend_movil.R
import com.nunez.abraham.bitmind_frontend_movil.databinding.FragmentPerfilBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendMessageButton.setOnClickListener {
            val userMessage = binding.messageInput.text.toString()
            if (userMessage.isNotBlank()) {
                sendMessageToApi(userMessage)
                Toast.makeText(requireContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "El mensaje no puede estar vacío", Toast.LENGTH_SHORT).show()
            }
        }

        binding.sendCleanButton.setOnClickListener {
            binding.messageInput.text.clear()
            Toast.makeText(requireContext(), "Texto limpiado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendMessageToApi(message: String) {
        val chatRequest = ChatRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(Message(role = "user", content = message)),
            max_tokens = 500
        )

        RetrofitInstanceIA.api.sendMessage(chatRequest).enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        val reply = it.choices.firstOrNull()?.message?.content ?: "No reply"
                        binding.responseTextView.text = reply
                    }
                } else {
                    binding.responseTextView.text = "Error: ${response.errorBody()?.string()}"
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                binding.responseTextView.text = "Error: ${t.message}"
            }
        })
    }
}