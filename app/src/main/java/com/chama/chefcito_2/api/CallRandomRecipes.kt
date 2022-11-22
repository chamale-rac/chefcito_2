package com.chama.chefcito_2.api

import com.chama.chefcito_2.model.random_recipes.RandomRecipeApiResponse
import com.chama.chefcito_2.utils.Constants.Companion.API_KEY
import com.chama.chefcito_2.utils.Constants.Companion.RETRIEVED
import retrofit2.http.GET
import retrofit2.http.Query

interface CallRandomRecipes {
    @GET("/recipes/random")
    suspend fun callRandomRecipe(@Query("apiKey") apiKey: String, @Query("number") number: String): RandomRecipeApiResponse
}