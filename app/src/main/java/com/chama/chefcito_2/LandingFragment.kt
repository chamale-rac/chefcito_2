package com.chama.chefcito_2

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.chama.chefcito_2.databinding.LandingFragmentBinding
import com.chama.chefcito_2.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


class LandingFragment : Fragment() {

    private var pink = "#FE6C6C"
    private var grey = "#4E4E4E"
    private lateinit var textView: TextView


    private lateinit var firebaseAuth: FirebaseAuth

    private var _binding: LandingFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LandingFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonLogin = binding.loginButton
        val buttonSignUp = binding.signUpButton

        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            findNavController().navigate(R.id.action_landing_fragment_to_feedFragment)
        }



        buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_landing_fragment_to_login_fragment)
        }

        buttonSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_landing_fragment_to_signup_fragment2)
        }

        textView = view.findViewById<TextView>(R.id.app_name)
        textView.text = Html.fromHtml(
            "<font color=${grey}>Chef</font>" +
                    "<font color=${pink}>cito</font>"
        )
    }

}