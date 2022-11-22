package com.chama.chefcito_2.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chama.chefcito_2.model.Post
import com.chama.chefcito_2.model.random_recipes.RandomRecipeApiResponse
import com.chama.chefcito_2.model.random_recipes.Recipes
import com.chama.chefcito_2.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<RandomRecipeApiResponse> = MutableLiveData()

    fun callRandomRecipe() {
        viewModelScope.launch {
            val response = repository.callRandomRecipe()
            myResponse.value = response
        }
    }

}