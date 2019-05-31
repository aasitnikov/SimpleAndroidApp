package ru.improvegroup.sixtyfivetest.ui.specialty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.android.MainActivity

class SpecialtyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_specialty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "SpecialtyFragment"
    }

    companion object {
        fun newInstance() = SpecialtyFragment()
    }
}