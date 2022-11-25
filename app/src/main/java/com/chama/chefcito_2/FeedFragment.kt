package com.chama.chefcito_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.adapter.FeedRecipeAdapter
import com.chama.chefcito_2.adapter.SavedFragmentAdapter
import com.chama.chefcito_2.databinding.FeedFragmentBinding
import com.chama.chefcito_2.model.FoodRecipe
import com.chama.chefcito_2.model.Recipe
import com.chama.chefcito_2.repository.Repository

import com.firebase.ui.firestore.FirestoreRecyclerOptions

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class FeedFragment : Fragment() {


    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var _binding: FeedFragmentBinding? = null

    private val binding get() = _binding!!
    var recipeAdapter: FeedRecipeAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private var db = FirebaseFirestore.getInstance()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        val query = db.collection("recipes")
        val options = FirestoreRecyclerOptions.Builder<Recipe>().setQuery(query, Recipe::class.java)
            .setLifecycleOwner(this).build()
        recyclerView = binding.recyclerViewPosts
        recipeAdapter = FeedRecipeAdapter(options)
        recyclerView  = binding.recyclerViewPosts
        recyclerView.adapter = recipeAdapter


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        recipeAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        recipeAdapter!!.stopListening()
    }

}