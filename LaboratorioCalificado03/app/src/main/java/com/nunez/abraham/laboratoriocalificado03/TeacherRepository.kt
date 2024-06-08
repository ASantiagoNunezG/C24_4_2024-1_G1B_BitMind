package com.nunez.abraham.laboratoriocalificado03

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TeacherRepository {
    private const val BASE_URL = "https://private-effe28-tecsup1.apiary-mock.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(TeacherApiService::class.java)

    suspend fun getTeachers(): List<Teacher> {
        return api.getTeachers().teachers
    }
}