package com.nunez.abraham.laboratoriocalificado03

import retrofit2.http.GET

interface TeacherApiService {
    @GET("list/teacher-b")
    suspend fun getTeachers(): TeacherResponse
}