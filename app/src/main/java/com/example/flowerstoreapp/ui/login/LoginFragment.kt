package com.example.flowerstoreapp.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowerstoreapp.R
import com.example.flowerstoreapp.databinding.FragmentCurrentBouquetBinding
import com.example.flowerstoreapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val model: LoginViewModel by viewModels()
    private var loginSuccessListener: OnLoginSuccessListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginSuccessListener) {
            loginSuccessListener = context
        } else {
            throw RuntimeException("$context must implement OnLoginSuccessListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btLogin.setOnClickListener {
            model.login(binding.etLogin.text.toString(), binding.etPassword.text.toString())
        }
        binding.btSignUp.setOnClickListener {
            navigateToRegistration()
        }
        observeViewModel()
        observeUserLoggedIn()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToRegistration() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun observeUserLoggedIn() {
        model.isUserLoggedIn.observe(viewLifecycleOwner) {
            loginSuccessListener?.onLoginSuccess()
        }
    }

    private fun observeViewModel() {
        model.isLoginError.observe(viewLifecycleOwner) { message ->
            message.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

}

public interface OnLoginSuccessListener {
    fun onLoginSuccess()
}