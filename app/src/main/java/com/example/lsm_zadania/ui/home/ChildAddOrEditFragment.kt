package com.example.lsm_zadania.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.gridlayout.widget.GridLayout
import com.example.lsm_zadania.R
import androidx.navigation.fragment.navArgs
import com.example.lsm_zadania.databinding.FragmentChildAddOrEditBinding
import com.example.lsm_zadania.ui.BaseFragment
import com.example.lsm_zadania.ui.notifyObserver
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.imageview.ShapeableImageView
import java.text.SimpleDateFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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

        for(index in drawableList.indices){
            var cardView: CardView = LayoutInflater.from(context).inflate(
                R.layout.item_avatar,
                binding.gridLayoutAvatarList,
                false
            ) as CardView

            val rowSpec = GridLayout.spec(index / 4, 1, 0.25f)
            val colSpec = GridLayout.spec(index % 4, 1, 0.25f)
            val myGPL= GridLayout.LayoutParams(rowSpec,colSpec)
            cardView.findViewWithTag<ImageView>("ShapeableImageView")?.setImageResource(drawableList[index])
            binding.gridLayoutAvatarList.addView(cardView,myGPL)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this

        observeShowDataPickerRequest()
    }

    private fun observeShowDataPickerRequest() {
        viewModel.showDatePickerRequest.observe(this.viewLifecycleOwner) {
            showBirthdayDataPicker()
        }
    }

    private fun showBirthdayDataPicker() {
        val title = resources.getString(R.string.select_birthdate)
        val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText(title).build()
        datePicker.addOnPositiveButtonClickListener { value ->
            viewModel.child.value?.let { child ->
                child.birthday = Date(value)
                viewModel.child.notifyObserver()
            }
        }

        datePicker.show(this.parentFragmentManager, "MY_DATE_PICKER_TAG")
    }
}