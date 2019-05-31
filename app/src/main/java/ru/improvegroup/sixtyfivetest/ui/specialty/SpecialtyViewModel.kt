package ru.improvegroup.sixtyfivetest.ui.specialty

import androidx.lifecycle.MutableLiveData
import ru.improvegroup.sixtyfivetest.domain.entity.Specialty
import ru.improvegroup.sixtyfivetest.domain.interactor.Interactor
import ru.improvegroup.sixtyfivetest.ui.common.BaseViewModel
import ru.improvegroup.sixtyfivetest.ui.common.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SpecialtyViewModel @Inject constructor(
    private val interactor: Interactor,
    private val router: Router
) : BaseViewModel() {

    val specialtyList = MutableLiveData<List<Specialty>>()

    init {
        interactor.getSpecialties()
            .subscribe(specialtyList::postValue, ::handleError)
            .untilDestroy()
    }

    fun navigateToList(specialityId: Int) {
        router.navigateTo(Screens.EmployeeList(specialityId))
    }
}