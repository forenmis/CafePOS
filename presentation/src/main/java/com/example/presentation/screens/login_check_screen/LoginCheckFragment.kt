package com.example.presentation.screens.login_check_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentBaseBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginCheckFragment : BaseFragment<FragmentBaseBinding, LoginCheckViewModel>() {

    override val viewModel by viewModel<LoginCheckViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBaseBinding {
        return FragmentBaseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { viewModel.screenFLow.collect { checkScreen(it) } }
    }

    private fun checkScreen(state: Action) {
        val action = when (state) {
            Action.ToHome -> LoginCheckFragmentDirections.actionLoginCheckFragmentToHomeFragment()
            Action.ToLogin -> LoginCheckFragmentDirections.actionLoginCheckFragmentToLoginFragment()
        }
        findNavController().navigate(action)
    }
}