package com.example.lsm_zadania.ui.home

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lsm_zadania.data.Child
import com.example.lsm_zadania.ui.BaseViewModel

class ChildAddOrEditViewModel : BaseViewModel() {

    private val _child = MutableLiveData<Child>()
    val child = _child

    override fun prepare(args: Bundle?) {
        super.prepare(args)
        _child.value = Child(name = "", behaviorPoints = 0, dutyPoints = 0, drawableName = "")
    }

}