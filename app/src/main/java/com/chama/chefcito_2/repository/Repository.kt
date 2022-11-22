package com.chama.chefcito_2.repository

import com.chama.chefcito_2.R
import com.chama.chefcito_2.api.RetrofitInstance
import com.chama.chefcito_2.model.Post
import com.chama.chefcito_2.model.random_recipes.RandomRecipeApiResponse
import com.chama.chefcito_2.utils.Constants.Companion.API_KEY

class Repository {
    suspend fun callRandomRecipe(): RandomRecipeApiResponse {
        return RetrofitInstance.api.callRandomRecipe(API_KEY, "10")
    }
}