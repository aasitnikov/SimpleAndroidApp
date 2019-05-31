package ru.improvegroup.sixtyfivetest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.main_fragment.*
import ru.improvegroup.sixtyfivetest.R
import ru.improvegroup.sixtyfivetest.ui.common.observe

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        bindViewModel()
    }

    private fun bindViewModel() {
        observe(viewModel.employeeList) { message.text = it.toString() }
        observe(viewModel.loading) { swipeRefreshLayout.isRefreshing = it }
        observe(viewModel.errorVisible) {
            message.isVisible = !it
            textView_error.isVisible = it
        }
        observe(viewModel.error) { textView_error.text = it }
    }

    private fun setupListeners() {
        swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
