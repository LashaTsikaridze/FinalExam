package com.example.finalexam.datebasa

data class Animal(
    val id: Int = 0,
    val name: String,
    val averageWeight: Double,
    val photoUri: String,    // ფოტოს Uri როგორც String (შეგიძლია სხვა ტიპი)
    val lifespan: Int,       // რამდენი ხანს ცოცხლობს (წელი)
    val description: String,
    val habitat: String
)
