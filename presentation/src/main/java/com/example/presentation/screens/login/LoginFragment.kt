package com.example.presentation.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentLoginBinding
import com.example.presentation.utils.getValue
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel by viewModel<LoginViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHaveAcc.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }
        binding.btLogin.setOnClickListener {
            if (checkFields()) {
                viewModel.login(
                    username = binding.etLogin.getValue(),
                    password = binding.etPassword.getValue()
                )
            }
        }
        lifecycleScope.launch {
            viewModel.userAuthFlow.collect {
                if (it) {
                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
            }
        }

    }

    private fun checkFields(): Boolean {
        val login = binding.etLogin.getValue()
        val password = binding.etPassword.getValue()
        return if (login.isEmpty() || password.isEmpty()) {
            showToast(R.string.error_fields)
            false
        } else {
            true
        }
    }

}