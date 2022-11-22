package com.chama.chefcito_2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.databinding.ItemPostViewBinding
import com.chama.chefcito_2.model.random_recipes.Recipes
import com.squareup.picasso.Picasso

class FoodListAdapter (private var recipesList: List<Recipes>) : RecyclerView.Adapter<FoodListAdapter.FoodListHolder>() {

    //private val list = apiRecipeList;
    inner class FoodListHolder(val binding: ItemPostViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListHolder {
        val binding = ItemPostViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodListHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodListHolder, position: Int) {
        val item = recipesList[position]

        holder.binding.foodName.text = item.title
        holder.binding.likeTextview.text = item.aggregateLikes.toString()
        holder.binding.saveTextview.text = item.servings.toString()
        holder.binding.timeTextview.text = StringBuilder().append(item.cookingMinutes.toString()).append("min")
        Picasso.get().load(item.image.toString()).into(holder.binding.foodPhoto)

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToInsideRecipeFragment(item.image.toString(), item.title.toString())
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    fun updateList(updatedList: List<Recipes>) {
        this.recipesList = updatedList
    }
}