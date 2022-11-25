package com.chama.chefcito_2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chama.chefcito_2.databinding.InsideRecipeFragmentBinding
import com.chama.chefcito_2.model.Recipe
import com.squareup.picasso.Picasso

class InsideRecipeFragment : Fragment() {

    private var _binding: InsideRecipeFragmentBinding? = null
    private val binding get() = _binding!!


    private lateinit var recipe: Recipe


    lateinit var stepsArr: ArrayList<String>
    lateinit var ingredientsArr: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if(bundle == null) {
            Log.e("KWA", "INFORMATION NOT RECEIVED")
            return
        }

        val args = InsideRecipeFragmentArgs.fromBundle(bundle)
        recipe = args.recipe
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = InsideRecipeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Picasso.get().load(recipe.image).into(binding.foodImage);
        binding.foodName.text = recipe.title
        stepsArr = recipe.steps
        ingredientsArr = recipe.ingredients

        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            stepsArr as List<String?>
        )

        binding.stepsListView.adapter = adapter
        adapter.notifyDataSetChanged()


        val buttonBack = binding.backButton
        buttonBack?.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}