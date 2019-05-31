package ru.improvegroup.sixtyfivetest.ui.specialty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_specialty.*
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.android.MainActivity
import ru.improvegroup.sixtyfivetest.ui.common.injectViewModel
import ru.improvegroup.sixtyfivetest.ui.common.observe

class SpecialtyListFragment : Fragment() {

    private val viewModel by lazy { injectViewModel(SpecialtyViewModel::class) }

    private val adapter = SpecialtyAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_specialty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.specialty_list_toolbar_title)
        setupRecyclerView()
        bindViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView_specialty.adapter = adapter
        recyclerView_specialty.layoutManager = LinearLayoutManager(context!!)
    }

    private fun bindViewModel() {
        observe(viewModel.specialtyList) { adapter.submitList(it) }
    }

    companion object {
        fun newInstance() = SpecialtyListFragment()
    }
}