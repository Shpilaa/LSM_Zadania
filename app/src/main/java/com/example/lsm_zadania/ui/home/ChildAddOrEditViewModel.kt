package com.example.lsm_zadania.ui.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lsm_zadania.data.Child
import com.example.lsm_zadania.ui.BaseViewModel
import com.example.lsm_zadania.ui.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ChildAddOrEditViewModel: BaseViewModel() {

    val showDatePickerRequest = SingleLiveEvent<Date>()

    private val _child = MutableLiveData<Child>()
    val child = _child

    override fun prepare(args: Bundle?) {
        super.prepare(args)
        _child.value = Child(
            name = "",
            behaviorPoints = 0,
            dutyPoints = 0,
            drawableName = "",
            birthday = Calendar.getInstance().time
        )
    }

    fun saveAddChildOrEdit() {
        Log.i("saveAddChildOrEdit", child.value.toString())
    }

    fun showDatePicker() {
        child.value?.let { showDatePickerRequest.postValue(it.birthday) }
    }

}