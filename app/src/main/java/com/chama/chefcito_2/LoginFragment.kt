package com.chama.chefcito_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.chama.chefcito_2.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LoginFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            findNavController().navigate(R.id.action_landing_fragment_to_feedFragment)
        }

        val buttonBack = binding.backButton
        val buttonNext = binding.buttonNext

        buttonBack.setOnClickListener{
            findNavController().navigate(R.id.action_login_fragment_to_landing_fragment)
        }

        buttonNext.setOnClickListener {
            val email = binding.outlinedTextFieldEmailV.editText?.text.toString()
            val password = binding.outlinedTextFieldPasswordV.editText?.text.toString()


            if (email.isNotBlank() && password.isNotBlank()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, "Logged In", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_login_fragment_to_feedFragment)
                    } else {
                        Toast.makeText(activity, "Failed to Log In ", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }

}