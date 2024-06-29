package com.nunez.abraham.bitmind_frontend_movil.IA

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ChatGptApi {
    @POST("v1/chat/completions")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse>
}
