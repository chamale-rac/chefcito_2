package com.chama.chefcito_2.api

import com.chama.chefcito_2.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: CallRandomRecipes by lazy {
        retrofit.create(CallRandomRecipes::class.java)
    }
}