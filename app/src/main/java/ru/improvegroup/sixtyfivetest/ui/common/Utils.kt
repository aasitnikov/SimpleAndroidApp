package ru.improvegroup.sixtyfivetest.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
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

fun <T : ViewModel> Fragment.injectViewModel(modelClass: KClass<T>, customScope: Any? = null): T {
    val factory = if (customScope == null) ToothpickViewModelFactory else ToothpickViewModelFactoryScoped(customScope)
    return ViewModelProviders.of(this, factory).get(modelClass.java)
}

fun ViewGroup.inflate(@LayoutRes res: Int): View = LayoutInflater.from(context).inflate(res, this, false)