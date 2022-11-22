package com.chama.chefcito_2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.R

class ProfileAdapter() : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var data = listOf("This","Is","Just","A", "Test", "Bruh", "Bruh", "Bruh", "Bruh", "This","Is","Just","A", "Test", "Bruh", "Bruh", "Bruh", "Bruh")

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image : ImageView
        var recipeName : TextView

        init {
            image = itemView.findViewById(R.id.profile_recipe_image)
            recipeName = itemView.findViewById(R.id.profile_recipe_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.profile_recipe_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var recipe = data[position]
        holder.recipeName.text = recipe

    }

    override fun getItemCount(): Int {
        return data.size
    }

}