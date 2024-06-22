package com.nunez.abraham.bitmind_frontend_movil

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nunez.abraham.bitmind_frontend_movil.databinding.ActivityHomeBinding
import com.nunez.abraham.bitmind_frontend_movil.fragments.AnunciosFragment
import com.nunez.abraham.bitmind_frontend_movil.fragments.ForosFragment
import com.nunez.abraham.bitmind_frontend_movil.fragments.PerfilFragment
import com.nunez.abraham.bitmind_frontend_movil.fragments.PublicacionesFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
        // Cargar el Fragment Publicaciones en el contenedor
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_init, PublicacionesFragment())
                .commit()
        }

        setForos()
        setAnuncios()
        setPerfil()
        setPublicaciones()
        */
        //COnfigurando propiedades de la pantalla

        // Configure NavController with BottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)

        //setFullscreen()
    }
    /*
    //METODOS PARA LOS NAVBARS
    //HOME
    private fun setPublicaciones() {
        binding.btnHome.setOnClickListener {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_init, PublicacionesFragment())
            .commit()
        }
    }
    //ANUNCIOS
    private fun setAnuncios(){
        binding.btnAnun.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_init,AnunciosFragment())
                .commit()
        }
    }
    //Foros
    private fun setForos(){
        binding.btnForo.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_init,ForosFragment())
                .commit()
        }
    }
    //Perfil
    private fun setPerfil(){
        binding.btnMe.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_init,PerfilFragment())
                .commit()
        }
    }*/


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