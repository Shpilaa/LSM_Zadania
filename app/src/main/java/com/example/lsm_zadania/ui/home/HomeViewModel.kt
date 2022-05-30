package com.example.lsm_zadania.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.FragmentNavigator
import com.example.lsm_zadania.ui.BaseFragment
import androidx.navigation.NavDirections
import com.example.lsm_zadania.data.Child
import com.example.lsm_zadania.ui.BaseViewModel
import com.example.lsm_zadania.ui.NavigationCommand
import java.util.*

class HomeViewModel : BaseViewModel() {

    private val _childList = MutableLiveData<List<Child>>()
    val childList : LiveData<List<Child>> = _childList

    init {
        fetchChildList()
    }

    private fun fetchChildList() {
        val newList = listOf(
            Child(name = "Bartek", dutyPoints = 9, behaviorPoints = 6, drawableName = "avatar1", birthday = Calendar.getInstance().time),
            Child(name = "Tomek", dutyPoints = 1, behaviorPoints = 7, drawableName = "avatar3", birthday = Calendar.getInstance().time),
            Child(name = "Asia", dutyPoints = 3, behaviorPoints = 3, drawableName = "avatar1", birthday = Calendar.getInstance().time),
            Child(name = "Zuzia", dutyPoints = 5, behaviorPoints = 2, drawableName = "avatar2", birthday = Calendar.getInstance().time)
        )
        _childList.value = newList
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun addNewChild(){
        var direction = HomeFragmentDirections.actionNavigationHomeToChildAddOrEditFragment()
        navigateTo(NavigationCommand.To(direction))
    }
}