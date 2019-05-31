package ru.improvegroup.sixtyfivetest.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.di.Scopes
import ru.improvegroup.sixtyfivetest.ui.main.MainFragment
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        Toothpick.inject(this, Toothpick.openScope(Scopes.APP))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        supportFragmentManager.addOnBackStackChangedListener { shouldDisplayHomeUp() }
        shouldDisplayHomeUp()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(SupportAppNavigator(this, CONTAINER_ID))
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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

    companion object {
        const val CONTAINER_ID = R.id.container
    }
}
