package com.chama.chefcito_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.FeedFragmentDirections
import com.chama.chefcito_2.R
import com.chama.chefcito_2.SavedFragmentDirections
import com.chama.chefcito_2.model.Recipe
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.lang.reflect.Field

class SavedFragmentAdapter(options: FirestoreRecyclerOptions<Recipe>) :
    FirestoreRecyclerAdapter<Recipe, SavedFragmentAdapter.SavedViewHolder>(options) {

    private var db = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        return SavedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post_view, parent, false))
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int, model: Recipe) {

        holder.title.text = model.title
        Picasso.get().load(model.image).into(holder.image)
        holder.likes.text  = model.liked.toString()
        holder.saved.text = model.saved.toString()
        firebaseAuth = FirebaseAuth.getInstance()

        val current_recipe = db.collection("recipes").document(model.docId)
        val current_user = firebaseAuth.currentUser?.email?.let { db.collection("users").document(it) }


        holder.itemView.setOnClickListener {

            val action = SavedFragmentDirections.actionSavedFragmentToInsideRecipeFragment(model)
            holder.itemView.findNavController().navigate(action)
        }
        holder.like_button.setOnClickListener {
            current_recipe
                .update("liked",FieldValue.increment(1))
        }
        holder.save_button.setOnClickListener {
            current_recipe
                .update("saved", FieldValue.increment(-1))
            current_user?.update("saved_recipes", FieldValue.arrayRemove(model.docId))
            

        }

    }

    class SavedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.food_name)
        val image = itemView.findViewById<ImageView>(R.id.food_photo)
        val likes = itemView.findViewById<TextView>(R.id.like_textview)
        val saved = itemView.findViewById<TextView>(R.id.save_textview)
        val like_button = itemView.findViewById<ImageButton>(R.id.like_button)
        val save_button = itemView.findViewById<ImageButton>(R.id.save_button)
    }

}