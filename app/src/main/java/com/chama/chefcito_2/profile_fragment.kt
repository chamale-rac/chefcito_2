package com.chama.chefcito_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class profile_fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var profileAdapter: ProfileAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.profileRecipesGrid)
        recyclerView.layoutManager = GridLayoutManager(view.context,3)
        recyclerView.adapter = ProfileAdapter()

        val buttonBack = view.findViewById<ImageButton>(R.id.backButtonProfile)
        buttonBack?.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_feedFragment)
        }

        val buttonProfileSetting = view.findViewById<ImageButton>(R.id.settingsButtonProfile)
        buttonProfileSetting?.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_profileSettingFragment)
        }
    }



}