package com.chama.chefcito_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.FeedFragmentDirections
import com.chama.chefcito_2.R
import com.chama.chefcito_2.model.Recipe
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class ProfileRecipeAdapter (options: FirestoreRecyclerOptions<Recipe>) :
    FirestoreRecyclerAdapter<Recipe, ProfileRecipeAdapter.RecipeViewHolder>(options) {

    private var db = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.profile_recipe_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int, model: Recipe) {
        firebaseAuth = FirebaseAuth.getInstance()

        holder.title.text = model.title
        Picasso.get().load(model.image).into(holder.image)

        val current_recipe = db.collection("recipes").document(model.docId)
        val current_user = firebaseAuth.currentUser?.email?.let { db.collection("users").document(it) }


        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToInsideRecipeFragment(model)
            holder.itemView.findNavController().navigate(action)
        }

    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.profile_recipe_name)
        val image = itemView.findViewById<ImageView>(R.id.profile_recipe_image)
    }



}