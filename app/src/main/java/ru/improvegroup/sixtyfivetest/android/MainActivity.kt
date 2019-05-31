package ru.improvegroup.sixtyfivetest.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.improvegroup.sixtyfivetest.domain.entity.Employee
import ru.improvegroup.sixtyfivetest.ui.employeedetails.EmployeeDetailsFragment
import ru.improvegroup.sixtyfivetest.ui.employeelist.EmployeeListFragment
import ru.improvegroup.sixtyfivetest.ui.main.MainFragment
import ru.improvegroup.sixtyfivetest.ui.specialty.SpecialtyListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ru.improvegroup.sixtyfivetest.R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(ru.improvegroup.sixtyfivetest.R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        supportFragmentManager.addOnBackStackChangedListener { shouldDisplayHomeUp() }
        shouldDisplayHomeUp()
    }

    //TODO: fix navigation
    fun navigateToList() {
        supportFragmentManager.beginTransaction()
            .replace(ru.improvegroup.sixtyfivetest.R.id.container, EmployeeListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    fun navigateToDetails(employee: Employee) {
        supportFragmentManager.beginTransaction()
            .replace(ru.improvegroup.sixtyfivetest.R.id.container, EmployeeDetailsFragment.newInstance(employee))
            .addToBackStack(null)
            .commit()
    }

    fun navigateToSpecialty() {
        supportFragmentManager.beginTransaction()
            .replace(ru.improvegroup.sixtyfivetest.R.id.container, SpecialtyListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        val canGoBack = supportFragmentManager.backStackEntryCount > 0
        supportActionBar!!.setDisplayHomeAsUpEnabled(canGoBack)
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
}
