package com.example.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.presentation.R
import com.example.presentation.screens.download_screen.DownloadDialog
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding ?: error("binding is null")

    protected abstract val viewModel: VM

    private var downloadDialog: DownloadDialog? = null

    protected abstract fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createViewBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.processFlow.collect {
                if (it) {
                    downloadDialog = DownloadDialog()
                    downloadDialog?.show(
                        parentFragmentManager,
                        DownloadDialog::class.java.simpleName
                    )
                } else {
                    downloadDialog?.dismiss()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.exceptionFlow.collect {
                showToast(R.string.error_fields)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    protected fun showToast(@StringRes text: Int) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}