package com.chama.chefcito_2

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.adapter.FeedRecipeAdapter
import com.chama.chefcito_2.adapter.SavedFragmentAdapter
import com.chama.chefcito_2.databinding.FeedFragmentBinding
import com.chama.chefcito_2.databinding.SavedFragmentBinding
import com.chama.chefcito_2.model.FoodRecipe
import com.chama.chefcito_2.model.Recipe
import com.chama.chefcito_2.repository.Repository
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore

class SavedFragment : Fragment() {


    private var _binding: SavedFragmentBinding? = null
    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    var recipeAdapter: SavedFragmentAdapter? = null
    private var myResults: MutableList<FoodRecipe> = Repository().getFoodSavedList()
    private var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SavedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        auth.currentUser?.email?.toString()?.let { Log.d("email", it) }


        var saved_recipes = arrayListOf<String>()

        val userRef = db.collection("users").document(auth.currentUser?.email.toString())
        userRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    saved_recipes = document.data?.get("saved_recipes") as ArrayList<String>
                    Log.d("Please",document.data?.get("username").toString())
                } else {
                    Log.d("Error", " such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Bruh", "get failed with ", exception)
            }
            .addOnCompleteListener {

                val query = db.collection("recipes").whereIn(FieldPath.documentId(), saved_recipes)
                val options = FirestoreRecyclerOptions.Builder<Recipe>().setQuery(query, Recipe::class.java)
                    .setLifecycleOwner(this).build()
                recyclerView = binding.recyclerViewPosts
                recipeAdapter = SavedFragmentAdapter(options)
                recyclerView = binding.recyclerViewPosts
                recyclerView.adapter = recipeAdapter
            }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}