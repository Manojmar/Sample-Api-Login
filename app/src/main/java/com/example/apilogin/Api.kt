package com.example.apilogin

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface Api {
    @POST("login1.php")
    fun loginn(@Body params: JsonObject): Call<Modal>

    companion object{
        var gson = GsonBuilder()
            .setLenient()
            .create()

        operator  fun invoke():Api{
            return Retrofit.Builder()
                .baseUrl("https://macappstudiotraining.com/manickaraj/api/products/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api::class.java)
        }
    }
}