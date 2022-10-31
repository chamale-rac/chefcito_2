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
import com.chama.chefcito_2.databinding.FeedFragmentBinding
import com.chama.chefcito_2.databinding.SavedFragmentBinding
import com.chama.chefcito_2.model.FoodRecipe
import com.chama.chefcito_2.repository.Repository

class SavedFragment: Fragment() {


    private var _binding: SavedFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private var myResults: MutableList<FoodRecipe> = Repository().getFoodSavedList()

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
        recyclerView = binding.recyclerViewPosts
        recyclerView.adapter = SavedListAdapter(myResults)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}