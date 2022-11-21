package com.chama.chefcito_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.databinding.FeedFragmentBinding
import com.chama.chefcito_2.model.FoodRecipe
import com.chama.chefcito_2.repository.Repository

class FeedFragment: Fragment() {


    private var _binding: FeedFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private var myResults: MutableList<FoodRecipe> = Repository().getFoodRecipeList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        recyclerView = binding.recyclerViewPosts
        recyclerView.adapter = FoodListAdapter(myResults)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}