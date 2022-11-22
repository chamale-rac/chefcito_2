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
import com.chama.chefcito_2.databinding.ProfileFragmentBinding
import com.chama.chefcito_2.databinding.SignupFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class profile_fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var profileAdapter: ProfileAdapter
    private var db = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

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

        recyclerView = view.findViewById(R.id.profileRecipesGrid)
        recyclerView.layoutManager = GridLayoutManager(view.context,3)
        recyclerView.adapter = ProfileAdapter()




        val buttonBack = binding.backButtonProfile

        buttonBack?.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_feedFragment)
        }

        val buttonProfileSetting = view.findViewById<ImageButton>(R.id.settingsButtonProfile)
        buttonProfileSetting?.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_profileSettingFragment)
        }




    }



}