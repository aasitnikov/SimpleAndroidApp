package ru.improvegroup.sixtyfivetest.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.improvegroup.sixtyfivetest.di.Scopes
import toothpick.Toothpick

object ToothpickViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Toothpick.openScope(Scopes.APP).getInstance(modelClass)
    }
}