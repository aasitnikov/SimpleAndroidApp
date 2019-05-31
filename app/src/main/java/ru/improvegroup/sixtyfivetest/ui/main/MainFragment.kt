package ru.improvegroup.sixtyfivetest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_fragment.*
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.ui.common.injectViewModel
import ru.improvegroup.sixtyfivetest.ui.common.observe

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        setupListeners()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(MainViewModel::class)
        bindViewModel()
    }

    private fun bindViewModel() {
        observe(viewModel.loading) {
            swipeRefreshLayout.isRefreshing = it
            button_list.isEnabled = !it
            button_specialty.isEnabled = !it
        }
        observe(viewModel.errorVisible) { textView_error.isVisible = it }
        observe(viewModel.error) { textView_error.text = it }
    }

    private fun setupListeners() {
        swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
        button_list.setOnClickListener { viewModel.navigateToList() }
        button_specialty.setOnClickListener { viewModel.navigateToSpecialty() }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
