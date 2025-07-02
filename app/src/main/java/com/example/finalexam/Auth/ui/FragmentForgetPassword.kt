package com.example.finalexam.Auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController

import com.example.finalexam.R
import com.google.firebase.auth.FirebaseAuth

class FragmentForgetPassword : Fragment(R.layout.fragment_forget_password) {

    private lateinit var emailEditText: EditText
    private lateinit var resetButton: Button
    private val auth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailEditText = view.findViewById(R.id.emailEditText)
        resetButton = view.findViewById(R.id.resetPasswordButton)
        resetButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "ელფოსტით გამოგზავნილია პაროლის აღდგენის ბმული",
                                Toast.LENGTH_LONG
                            ).show()

                        } else {
                            Toast.makeText(
                                requireContext(),
                                "შეცდომა: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    requireContext(),
                    "გთხოვთ შეიყვანოთ თქვენი ელფოსტა",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_fragmentForgetPassword_to_fragmentSing)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}