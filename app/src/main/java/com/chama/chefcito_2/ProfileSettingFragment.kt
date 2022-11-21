package com.chama.chefcito_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.chama.chefcito_2.R
import com.chama.chefcito_2.databinding.LandingFragmentBinding
import com.chama.chefcito_2.databinding.ProfileSettingsFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileSettingFragment : Fragment() {


    private lateinit var firebaseAuth: FirebaseAuth

    private var _binding: ProfileSettingsFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileSettingsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = navOptions {
        }
        firebaseAuth = FirebaseAuth.getInstance()

        val buttonBack = binding.backButton
        val buttonSignout = binding.profileButtonSignout

        buttonBack.setOnClickListener{
            findNavController().navigate(R.id.action_profileSettingFragment_to_profileFragment)
        }
        buttonSignout.setOnClickListener{
            firebaseAuth.signOut()
            findNavController().navigate(R.id.action_profileSettingFragment_to_landing_fragment)
        }


    }

}