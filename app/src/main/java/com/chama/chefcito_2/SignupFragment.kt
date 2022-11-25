package com.chama.chefcito_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chama.chefcito_2.databinding.SignupFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private var db = FirebaseFirestore.getInstance()
    private var _binding: SignupFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignupFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonBack = binding.backButton

        buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_signup_fragment2_to_landing_fragment)
        }

        val buttonNext = binding.buttonNext

        firebaseAuth = FirebaseAuth.getInstance()

        buttonNext?.setOnClickListener {

            val email = binding.outlinedTextFieldEmailV.editText?.text.toString()
            val password = binding.outlinedTextFieldPassword.editText?.text.toString()
            val username = binding.outlinedTextFieldUsernameV.editText?.text.toString()
            val confirmPass = binding.outlinedTextFieldPasswordConfirm.editText?.text.toString()

            if (email.isNotBlank() && password.isNotBlank() && password.equals(confirmPass)) {
                db.collection("users").document(email).set(
                    hashMapOf("username" to username, "recipes" to arrayListOf("A"), "saved_recipes" to arrayListOf("Z"))
                )
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, "Succesfully Registered", Toast.LENGTH_LONG).show()

                        firebaseAuth.signOut()
                        findNavController().navigate(R.id.action_signup_fragment2_to_login_fragment)
                    } else {
                        Toast.makeText(activity, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }

    }


}