package ru.improvegroup.sixtyfivetest.ui.main

import androidx.lifecycle.MutableLiveData
import ru.improvegroup.sixtyfivetest.domain.interactor.Interactor
import ru.improvegroup.sixtyfivetest.ui.common.BaseViewModel
import ru.improvegroup.sixtyfivetest.ui.common.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: Interactor,
    private val router: Router
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

    fun navigateToSpecialty() {
        if (loading.value != false) return
        router.navigateTo(Screens.SpecialtyList())
    }
}
