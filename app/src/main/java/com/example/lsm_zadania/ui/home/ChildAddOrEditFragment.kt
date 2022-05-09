package com.example.lsm_zadania.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lsm_zadania.R
import com.example.lsm_zadania.databinding.FragmentChildAddOrEditBinding
import com.example.lsm_zadania.ui.BaseFragment

class ChildAddOrEditFragment : BaseFragment<ChildAddOrEditViewModel>(ChildAddOrEditViewModel::class.java) {
//    companion object {
//        fun newInstance() = ChildAddOrEditFragment()
//    }

    private var _binding: FragmentChildAddOrEditBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChildAddOrEditBinding.inflate(inflater, container, false)
        createAvatarsCardToGridLayout()

        return binding.root
    }

    private fun createAvatarsCardToGridLayout() {
        var drawableList = listOf(
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3
        )
    }

}