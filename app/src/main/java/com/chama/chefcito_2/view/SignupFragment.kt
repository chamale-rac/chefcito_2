package com.chama.chefcito_2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chama.chefcito_2.R

class SignupFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signup_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonBack = view.findViewById<Button>(R.id.backButton)
        buttonBack?.setOnClickListener {
            findNavController().navigate(R.id.action_signup_fragment2_to_landing_fragment)
        }

        val buttonNext = view.findViewById<Button>(R.id.button_next)
        buttonNext?.setOnClickListener {
            findNavController().navigate(R.id.action_signup_fragment2_to_feedFragment)
        }
    }


}