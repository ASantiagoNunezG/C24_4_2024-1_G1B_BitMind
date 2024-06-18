package com.nunez.abraham.bitmind_frontend_movil

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nunez.abraham.bitmind_frontend_movil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar el Fragment login en el contenedor
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }
        // Configurar modo inmersivo
        setFullscreen()
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