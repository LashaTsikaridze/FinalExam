package com.example.finalexam.datebasa

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val id: Int = 0,
    val name: String,
    val averageWeight: Double,
    val photoUri: String,
    val lifespan: Int,
    val description: String,
    val habitat: String
) : Parcelable
