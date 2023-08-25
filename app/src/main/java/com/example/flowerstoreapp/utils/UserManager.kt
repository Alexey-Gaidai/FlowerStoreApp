package com.example.flowerstoreapp.utils

import android.content.SharedPreferences

class UserManager(private val sharedPreferences: SharedPreferences) {

    fun saveUser(id: String, name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("user_id", id)
        editor.putString("user_name", name)
        editor.apply()
    }

    fun getUserId(): String {
        return sharedPreferences.getString("id", null)!!
    }

    fun getUserName(): String {
        return sharedPreferences.getString("name", null)!!
    }

    fun clearUserData() {
        val editor = sharedPreferences.edit()
        editor.remove("id")
        editor.remove("name")
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return !getUserId().isBlank()
    }
}