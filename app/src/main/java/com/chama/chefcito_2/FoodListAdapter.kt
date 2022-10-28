package com.chama.chefcito_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.databinding.ItemPostViewBinding
import com.chama.chefcito_2.model.FoodRecipe
import com.squareup.picasso.Picasso

class FoodListAdapter (private val pokemonList: List<FoodRecipe>) : RecyclerView.Adapter<FoodListAdapter.FoodListHolder>() {

    private val list = pokemonList;
    inner class FoodListHolder(val binding: ItemPostViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListHolder {
        val binding = ItemPostViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodListHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodListHolder, position: Int) {
        val item = list[position]

        holder.binding.foodName.text = item.name
        holder.binding.likeTextview.text = StringBuilder().append(item.likes.toString()).append("k")
        holder.binding.saveTextview.text = StringBuilder().append(item.saves.toString()).append("k")
        holder.binding.timeTextview.text = StringBuilder().append(item.time.toString()).append("min")
        Picasso.get().load(item.image).into(holder.binding.foodPhoto);
    }

    override fun getItemCount(): Int {
        return list.size
    }
}