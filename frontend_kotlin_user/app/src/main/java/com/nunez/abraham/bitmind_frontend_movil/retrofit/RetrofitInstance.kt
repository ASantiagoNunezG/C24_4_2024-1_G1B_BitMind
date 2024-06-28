package com.nunez.abraham.bitmind_frontend_movil.retrofit

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import com.nunez.abraham.bitmind_frontend_movil.apis.AnuncioApi
import com.nunez.abraham.bitmind_frontend_movil.apis.ForoApi
import com.nunez.abraham.bitmind_frontend_movil.apis.PublicacionApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.time.LocalDateTime



object RetrofitInstance {
    private const val BASE_URL = "http://192.168.18.13:8080/"
    //private const val BASE_URL = "http://192.168.56.1:8080/"
    //private const val BASE_URL = "http://10.200.173.225:8080/"


    @RequiresApi(Build.VERSION_CODES.O)
    private val gson = GsonBuilder()

        //Dando formato a la fecha
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter())
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val publicacionApi: PublicacionApi by lazy {
        retrofit.create(PublicacionApi::class.java)
    }
    val anuncioApi: AnuncioApi by lazy {
        retrofit.create(AnuncioApi::class.java)
    }
    val foroApi: ForoApi by lazy {
        retrofit.create(ForoApi::class.java)
    }

    /*
    val usuarioApi: UsuarioApi by lazy {
        retrofit.create(UsuarioApi::class.java)
    }*/
}