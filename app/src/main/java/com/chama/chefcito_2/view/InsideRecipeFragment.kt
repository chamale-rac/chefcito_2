package com.chama.chefcito_2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chama.chefcito_2.R
import com.chama.chefcito_2.databinding.InsideRecipeFragmentBinding
import com.squareup.picasso.Picasso

class InsideRecipeFragment : Fragment() {

    companion object {
        val IMAGE = "image"
        val TITLE = "title"
    }

    private var _binding: InsideRecipeFragmentBinding? = null
    private val binding get() = _binding!!


    private lateinit var image: String
    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(IMAGE).toString()
            title = it.getString(TITLE).toString()
        }
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
        Picasso.get().load(image).into(binding.foodImage);
        binding.foodName.text = title

        val buttonBack = view.findViewById<Button>(R.id.backButton2)
        buttonBack?.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}