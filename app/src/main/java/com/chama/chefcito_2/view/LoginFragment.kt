package com.chama.chefcito_2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.chama.chefcito_2.R


class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonBack = view.findViewById<Button>(R.id.backButton)
        buttonBack?.setOnClickListener{
            findNavController().navigate(R.id.action_login_fragment_to_landing_fragment)
        }

        val buttonNext = view.findViewById<Button>(R.id.button_next)
        buttonNext?.setOnClickListener {
            findNavController().navigate(R.id.action_login_fragment_to_feedFragment)
        }
    }

}