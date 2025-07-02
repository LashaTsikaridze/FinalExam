package com.example.finalexam.Main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FragmentProfile : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("users")

        val user = auth.currentUser
        if (user != null) {
            binding.tvEmail.text = "Email: ${user.email ?: "დაფიქსირებული არ არის"}"

            // წამოვიღოთ მონაცემები Realtime Database-დან
            database.child(user.uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val firstName = snapshot.child("firstName").getValue(String::class.java) ?: ""
                        val lastName = snapshot.child("lastName").getValue(String::class.java) ?: ""
                        val age = snapshot.child("age").getValue(Long::class.java)?.toString() ?: ""
                        val gender = snapshot.child("gender").getValue(String::class.java) ?: ""

                        binding.tvFirstName.text = "სახელი: $firstName"
                        binding.tvLastName.text = "გვარი: $lastName"
                        binding.tvAge.text = "ასაკი: $age"
                        binding.tvGender.text = "სქესი: $gender"
                    } else {
                        binding.tvFirstName.text = "მონაცემები არ არის"
                        binding.tvLastName.text = ""
                        binding.tvAge.text = ""
                        binding.tvGender.text = ""
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    binding.tvFirstName.text = "მონაცემების წასაკითხად შეცდომაა"
                    binding.tvLastName.text = ""
                    binding.tvAge.text = ""
                    binding.tvGender.text = ""
                }
            })
        } else {
            binding.tvEmail.text = "მომხმარებელი არ არის შესული"
            binding.tvFirstName.text = ""
            binding.tvLastName.text = ""
            binding.tvAge.text = ""
            binding.tvGender.text = ""
        }

        // გამოსვლის ღილაკის დაჭერის ლოგიკა
        binding.Singout.setOnClickListener {
            auth.signOut()
            // აქ შეგიძლია გადაფრენა ლოგინის ან სხვა ფრაგმენტზე
            // მაგალითად, თუ იყენებ Navigation Component-ს:
            findNavController().navigate(R.id.action_fragmentProfile2_to_fragmentSing)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

