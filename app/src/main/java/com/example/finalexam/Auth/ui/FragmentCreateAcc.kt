package com.example.finalexam.Auth.ui

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalexam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FragmentCreateAcc : Fragment(R.layout.fragment_create_acc) {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstNameEditText = view.findViewById(R.id.firstNameEditText)
        lastNameEditText = view.findViewById(R.id.lastNameEditText)
        ageEditText = view.findViewById(R.id.ageEditText)
        genderSpinner = view.findViewById(R.id.genderSpinner)
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText)
        registerButton = view.findViewById(R.id.registerButton)

        auth = FirebaseAuth.getInstance()

        setupGenderSpinner()

        registerButton.setOnClickListener {
            registerUser()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_fragmentSing_to_fragmentHome)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setupGenderSpinner() {
        val genders = listOf("მამრობითი", "მდედრობითი", "სხვა")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = adapter
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
        return password.matches(passwordPattern.toRegex())
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun registerUser() {
        val firstName = firstNameEditText.text.toString().trim()
        val lastName = lastNameEditText.text.toString().trim()
        val ageStr = ageEditText.text.toString().trim()
        val gender = genderSpinner.selectedItem.toString()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || ageStr.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(context, "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()
            return
        }

        val age = ageStr.toIntOrNull()
        if (age == null || age <= 0) {
            Toast.makeText(context, "გთხოვთ შეიყვანოთ სწორი ასაკი", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidEmail(email)) {
            Toast.makeText(context, "ელ-ფოსტა არასწორია", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidPassword(password)) {
            Toast.makeText(
                context,
                "პაროლი უნდა შეიცავდეს მინიმუმ 8 სიმბოლოს, დიდ და პატარა ასოებს, ციფრს და სპეციალურ სიმბოლოს",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(context, "პაროლები არ ემთხვევა", Toast.LENGTH_SHORT).show()
            return
        }

        // რეგისტრაცია Firebase Authentication-ში
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Firebase User ID-ს მიღება
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        // მონაცემების შენახვა Firebase Realtime Database-ში
                        val database = FirebaseDatabase.getInstance()
                        val usersRef = database.getReference("users")

                        val userMap = mapOf(
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "age" to age,
                            "gender" to gender,
                            "email" to email
                        )

                        usersRef.child(userId).setValue(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(context, "მომხმარებელი წარმატებით დარეგისტრირდა", Toast.LENGTH_SHORT).show()
                                findNavController().navigate(R.id.action_fragmentCreateAcc_to_fragmentSing)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "მონაცემების შენახვის შეცდომა: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    Toast.makeText(context, "რეგისტრაციის შეცდომა: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
