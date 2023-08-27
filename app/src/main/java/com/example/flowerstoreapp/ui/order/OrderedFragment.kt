package com.example.flowerstoreapp.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowerstoreapp.R
import com.example.flowerstoreapp.databinding.FragmentOrderBinding
import com.example.flowerstoreapp.databinding.FragmentOrderedBinding


class OrderedFragment : Fragment() {
    private var _binding: FragmentOrderedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btOk.setOnClickListener {
            findNavController().navigate(R.id.action_orderedFragment_to_navigation_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}