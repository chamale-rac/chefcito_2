package com.chama.chefcito_2.repository

import com.chama.chefcito_2.model.FoodRecipe

class Repository {
    fun getFoodRecipeList(): MutableList<FoodRecipe> {
        return mutableListOf(
            FoodRecipe(1, "Hamburger and fries", 5, 2, 60,"https://images.eatsmarter.com/sites/default/files/styles/1600x1200/public/classic-hamburger-and-fries-604088.jpg"),
            FoodRecipe(2, "Pizza", 7, 1, 30,"https://www.saborusa.com/wp-content/uploads/2019/12/origen-de-la-pizza-1.jpg"),
            FoodRecipe(3, "C&C Ice Cream",3, 2, 50, "https://www.kingarthurbaking.com/sites/default/files/2022-05/cookies-and-cream-ice-cream_0422.jpg"),
            FoodRecipe(4, "Linguini 'Aglio e Olio'" , 5, 2, 20,"https://lacocinanomuerde.com/images/recetas/linguiniaglio2015.jpg"),
            FoodRecipe(5, "Kak'ik de pollo",52, 8, 30, "https://alimentatedelobueno.com/storage/778/kakik.png"),
            FoodRecipe(6, "Homemade Ramen",2, 2, 40, "https://www.killingthyme.net/wp-content/uploads/2015/10/homemade-ramen-bowls-5.jpg")
        )
    }
}