package com.chama.chefcito_2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.adapter.ProfileRecipeAdapter
import com.chama.chefcito_2.adapter.SavedFragmentAdapter
import com.chama.chefcito_2.databinding.ProfileFragmentBinding
import com.chama.chefcito_2.databinding.SignupFragmentBinding
import com.chama.chefcito_2.model.Recipe
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = FirebaseFirestore.getInstance()
    private var _binding: ProfileFragmentBinding? = null


    private val binding get() = _binding!!


    var recipeAdapter: ProfileRecipeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        val docRef = db.collection("users").document(firebaseAuth.currentUser?.email.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.profileName.setText(document.data?.get("username").toString())
                } else {
                    Log.d("Error", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Bruh", "get failed with ", exception)
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        var liked_recipes = arrayListOf<String>()
        val userRef = db.collection("users").document(firebaseAuth.currentUser?.email.toString())


        userRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    liked_recipes = document.data?.get("recipes") as ArrayList<String>
                    Log.d("Please",document.data?.get("username").toString())
                } else {
                    Log.d("Error", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Bruh", "get failed with ", exception)
            }
            .addOnCompleteListener {

                val query = db.collection("recipes").whereIn(FieldPath.documentId(), liked_recipes)
                val options = FirestoreRecyclerOptions.Builder<Recipe>().setQuery(query, Recipe::class.java)
                    .setLifecycleOwner(this).build()
                recyclerView = binding.profileRecipesGrid
                recipeAdapter = ProfileRecipeAdapter(options)
                recyclerView = binding.profileRecipesGrid
                recyclerView.adapter = recipeAdapter
                recyclerView.layoutManager = GridLayoutManager(view.context, 3)
            }


        val buttonBack = binding.backButtonProfile

        buttonBack?.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_feedFragment)
        }

        val buttonProfileSetting = view.findViewById<ImageButton>(R.id.settingsButtonProfile)
        buttonProfileSetting?.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_profileSettingFragment)
        }


    }


}