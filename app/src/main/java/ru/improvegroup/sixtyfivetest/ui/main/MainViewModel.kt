package ru.improvegroup.sixtyfivetest.ui.main

import androidx.lifecycle.MutableLiveData
import ru.improvegroup.sixtyfivetest.domain.interactor.Interactor
import ru.improvegroup.sixtyfivetest.ui.common.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: Interactor
) : BaseViewModel() {

    val loading = MutableLiveData(false)
    val errorVisible = MutableLiveData(false)

    init {
        loadEmployees()
    }

    private fun loadEmployees() {
        interactor.fetchFromApi()
            .bindProgress(loading)
            .subscribe(
                {
                    errorVisible.postValue(false)
                },
                {
                    errorVisible.postValue(true)
                    handleError(it)
                })
            .untilDestroy()
    }

    fun onRefresh() = loadEmployees()
}
