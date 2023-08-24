package com.example.flowerstoreapp.domain.models

data class UserRegistration(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val password: String
)
