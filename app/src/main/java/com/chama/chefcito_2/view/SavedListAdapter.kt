package com.chama.chefcito_2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.databinding.ItemPostViewBinding
import com.chama.chefcito_2.model.random_recipes.Recipes
import com.squareup.picasso.Picasso

class SavedListAdapter (private var recipesList: List<Recipes>) : RecyclerView.Adapter<SavedListAdapter.SavedListHolder>() {

    //private val list = apiRecipeList;
    inner class SavedListHolder(val binding: ItemPostViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedListAdapter.SavedListHolder {
        val binding = ItemPostViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedListHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedListHolder, position: Int) {
        val item = recipesList[position]

        holder.binding.foodName.text = item.title
        holder.binding.likeTextview.text = StringBuilder().append(item.aggregateLikes.toString()).append("k")
        holder.binding.saveTextview.text = StringBuilder().append(item.servings.toString()).append("k")
        holder.binding.timeTextview.text = StringBuilder().append(item.cookingMinutes.toString()).append("min")
        Picasso.get().load(item.image).into(holder.binding.foodPhoto);

        holder.itemView.setOnClickListener {
            val action = SavedFragmentDirections.actionSavedFragmentToInsideRecipeFragment(item.image.toString(), item.title.toString())
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    fun updateList(apiRecipeList: List<Recipes>) {
        this.recipesList = apiRecipeList
    }
}