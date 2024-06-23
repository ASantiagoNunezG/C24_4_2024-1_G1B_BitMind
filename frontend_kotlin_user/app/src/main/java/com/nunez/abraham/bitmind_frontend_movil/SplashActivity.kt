package com.nunez.abraham.bitmind_frontend_movil

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.nunez.abraham.bitmind_frontend_movil.databinding.ActivitySplashBinding
import com.rommansabbir.animationx.Attention
import com.rommansabbir.animationx.animationXAttention

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bitmindLogo.animationXAttention(Attention.ATTENTION_PULSE)
        runPostDelayed()

        // Configurar modo inmersivo
        setFullscreen()

    }
    private fun runPostDelayed() {
        Handler(Looper.getMainLooper()).postDelayed({
            goMainActivity()
        }, 3000)
    }

    //Despues de la presentacion me inicializa en la activity Main
    private fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //Para la pantalla completa
    @RequiresApi(Build.VERSION_CODES.R)
    private fun setFullscreen() {
        window.insetsController?.let { controller ->
            // Ocultar barra de navegación y barra de estado
            controller.hide(WindowInsets.Type.navigationBars())
            // Configurar el comportamiento de ocultar la barra de navegación
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}