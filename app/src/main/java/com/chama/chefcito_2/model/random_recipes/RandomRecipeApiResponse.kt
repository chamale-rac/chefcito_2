package com.chama.chefcito_2.model.random_recipes

import com.google.gson.annotations.SerializedName

data class RandomRecipeApiResponse(
    @SerializedName("recipes" ) val recipes : MutableList<Recipes>
)
