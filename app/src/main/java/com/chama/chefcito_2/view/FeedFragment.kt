package com.chama.chefcito_2.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.chama.chefcito_2.databinding.FeedFragmentBinding
import com.chama.chefcito_2.repository.Repository
import kotlinx.coroutines.Dispatchers.Main

class FeedFragment: Fragment() {
    private var _binding: FeedFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: FoodListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.myResponse.observe(this, Observer {  response ->
            adapter.updateList(response.recipes)
            adapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
            binding.recyclerViewPosts.visibility = View.VISIBLE
        })
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
        adapter = FoodListAdapter(emptyList())
        recyclerView.adapter = adapter
        viewModel.callRandomRecipe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}