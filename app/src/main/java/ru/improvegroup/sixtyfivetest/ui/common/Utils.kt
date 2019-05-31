package ru.improvegroup.sixtyfivetest.ui.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

fun <T : Any?> Fragment.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        it?.let(observer)
    })
}

fun <T : ViewModel> Fragment.injectViewModel(modelClass: KClass<T>): T {
    return ViewModelProviders.of(this, ToothpickViewModelFactory).get(modelClass.java)
}