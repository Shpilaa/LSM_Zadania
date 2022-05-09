package com.example.lsm_zadania.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lsm_zadania.databinding.FragmentHomeBinding
import com.example.lsm_zadania.ui.BaseFragment
import com.example.lsm_zadania.utils.ResourceUtils

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class.java) {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel
        binding.resourceUtils = ResourceUtils
        binding.lifecycleOwner = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}