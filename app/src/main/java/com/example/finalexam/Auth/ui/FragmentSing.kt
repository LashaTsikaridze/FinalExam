package com.example.finalexam.Auth.ui


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalexam.R
import com.google.firebase.auth.FirebaseAuth


class FragmentSing : Fragment(R.layout.fragment_sing) {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button
    private lateinit var createAccountButton: Button

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private fun isValidEmail(email: String): Boolean {
        return email.matches(emailPattern.toRegex())
    }

    private fun loginUser(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "წარმატებული ავტორიზაცია", Toast.LENGTH_SHORT).show()
                    emailEditText.text.clear()
                    passwordEditText.text.clear()
                    findNavController().navigate(R.id.action_fragmentSing_to_fragmentHome)
                } else {
                    Toast.makeText(requireContext(), "შეცდომა: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        emailEditText = view.findViewById(R.id.username)
        passwordEditText = view.findViewById(R.id.password)
        signInButton = view.findViewById(R.id.login)
        signUpButton = view.findViewById(R.id.singup)
        createAccountButton = view.findViewById(R.id.createacc)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (isValidEmail(email)) {
                    loginUser(email, password)
                } else {
                    Toast.makeText(requireContext(), "ელფოსტის ფორმატი არასწორია", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()
            }
        }

        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.fragmentCreateAcc)
        }

        createAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSing_to_fragmentForgetPassword)
        }
    }
}
