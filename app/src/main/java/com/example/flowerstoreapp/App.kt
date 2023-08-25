package com.example.flowerstoreapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.flowerstoreapp.utils.UserManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var userManager: UserManager
            private set
        lateinit var sharedPreferences: SharedPreferences
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initSharedPreferences()
        initTokenManager()
    }

    private fun initTokenManager() {
        userManager = UserManager(sharedPreferences)
    }

    private fun initSharedPreferences() {
        sharedPreferences = getSharedPreferences("UserSharedPreferences", Context.MODE_PRIVATE)
    }
}