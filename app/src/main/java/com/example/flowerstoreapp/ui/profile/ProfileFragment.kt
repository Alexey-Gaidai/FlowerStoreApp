package com.example.flowerstoreapp.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowerstoreapp.databinding.FragmentProfileBinding
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsAdapter
import com.example.flowerstoreapp.ui.login.OnLoginSuccessListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val model: ProfileViewModel by viewModels()
    private val adapter = ProfileOrdersAdapter{}
    private var logoutListener: OnLogoutListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLogoutListener) {
            logoutListener = context
        } else {
            throw RuntimeException("$context must implement OnLogoutListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initRecyclerView()
        binding.btLogout.setOnClickListener {
            logoutListener?.onLogoutClicked()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            model.loadOrders()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        model.orders.observe(viewLifecycleOwner) { bouquets ->
            adapter.submitList(bouquets)
            binding.swipeRefreshLayout.isRefreshing = false
        }
        model.profile.observe(viewLifecycleOwner) {
            binding.tvName.text = it.firstName
            binding.tvLastname.text = it.lastName
            binding.tvPhone.text = it.phone
        }
    }

    private fun initRecyclerView() {
        binding.rvOrders.adapter = adapter
        binding.rvOrders.layoutManager = LinearLayoutManager(context)
    }

}

interface OnLogoutListener {
    fun onLogoutClicked()
}