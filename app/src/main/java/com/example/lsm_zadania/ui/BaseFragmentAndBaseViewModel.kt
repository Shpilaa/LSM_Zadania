package com.example.lsm_zadania.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import com.example.lsm_zadania.R
import java.util.concurrent.atomic.AtomicBoolean

open class BaseViewModel : ViewModel() {
    val navigationCommands = SingleLiveEvent<NavigationCommand>()

    open fun prepare(args: Bundle?) {
        Log.w("App", "prepare: ${this.javaClass.simpleName}")
    }

    fun navigateTo(directions: NavigationCommand) {
        navigationCommands.postValue(directions)
    }

}

open class BaseFragment<T: BaseViewModel>(private val vmType: Class<T>) : Fragment() {

    open fun activityModel() = false

    protected lateinit var viewModel: T
    private val navController by lazy {
        requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        if (activityModel())
            viewModel = ViewModelProvider(requireActivity()).get(vmType)
        else
            viewModel = ViewModelProvider(this).get(vmType)

        this.viewModel.prepare(arguments)

        this.viewModel.navigationCommands.observe(viewLifecycleOwner, Observer { command ->
            resolveNavigationCommand(command)
        })
    }

    private fun resolveNavigationCommand(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> {
                navController.navigate(command.directions)
            }
        }
    }

}

sealed class NavigationCommand {
    data class To(val directions : NavDirections) : NavigationCommand()
}


class SingleLiveEvent<T> : MutableLiveData<T> () {
    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes")
        }

        super.observe(owner, Observer { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "com.example.lsm_zadania.ui.SingleLiveEvent"
    }
}

