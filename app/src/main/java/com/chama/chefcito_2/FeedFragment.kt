package com.chama.chefcito_2

import androidx.fragment.app.Fragment
import com.chama.chefcito_2.model.FoodRecipe
import com.chama.chefcito_2.repository.Repository

class FeedFragment: Fragment() {

    private var myResults: MutableList<FoodRecipe> = Repository().getFoodRecipeList()


}