package com.example.presentation.screens.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentRegistrationBinding
import com.example.presentation.utils.getValue
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    private val viewModel by viewModel<RegistrationViewModel>()
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHaveAcc.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        lifecycleScope.launch {
            viewModel.userAuthFlow.collect {
                if (it) {
                    val action =
                        RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
            }
        }
        binding.btLogin.setOnClickListener {
            if (checkFields()) {
                viewModel.registration(
                    username = binding.etLogin.getValue(),
                    email = binding.etEmail.getValue(),
                    password = binding.etPassword.getValue()
                )
            } else {
                showToast(R.string.error_fields)
            }
        }
    }

    private fun checkFields(): Boolean {
        val login = binding.etLogin.getValue()
        val email = binding.etEmail.getValue()
        val password = binding.etPassword.getValue()
        return if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            showToast(R.string.error_fields)
            false
        } else {
            true
        }
    }
}