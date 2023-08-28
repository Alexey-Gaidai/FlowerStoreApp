package com.example.flowerstoreapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.flowerstoreapp.databinding.ActivityMainBinding
import com.example.flowerstoreapp.ui.login.OnLoginSuccessListener
import com.example.flowerstoreapp.ui.profile.OnLogoutListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnLoginSuccessListener{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_login)
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.login_navigation)
        navController.graph = graph
    }

    override fun onLoginSuccess() {
        val intent = Intent(this, StoreActivity::class.java)
        startActivity(intent)
    }


}