package com.example.presentation.screens.home.create

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.BundleCompat
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.domain.entity.PortionType
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentCreateMenuItemBinding
import com.example.presentation.screens.home.create.category_bottom_sheet.SelectCategoryBottomSheet
import com.example.presentation.screens.home.create.type_portion__bottom_sheet.SelectTypePortionBottomSheet
import com.example.presentation.utils.getValue
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateMenuItemFragment : BaseFragment<FragmentCreateMenuItemBinding>() {
    private lateinit var launcher: ActivityResultLauncher<Uri>

    private val viewModel by viewModel<CreateMenuViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCreateMenuItemBinding {
        return FragmentCreateMenuItemBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contract = ActivityResultContracts.TakePicture()
        launcher = registerForActivityResult(contract) { result ->
            if (result) {
                viewModel.pathFlow.value?.let { setPhoto(it) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(REQUEST_CATEGORY) { _, bundle ->
            val id = bundle.getLong(BUNDLE_KEY_CATEGORY)
            viewModel.loadCategoryById(id)
        }
        setFragmentResultListener(REQUEST_TYPE) { _, bundle ->
            val portionType =
                BundleCompat.getParcelable(bundle, BUNDLE_KEY_TYPE, PortionType::class.java)
                    ?: error("empty parcelable")
            binding.btTypePortion.text = portionType.shortName
            viewModel.saveTypePortion(portionType)
        }
        binding.btCategory.setOnClickListener {
            val categoryBottomSheet = SelectCategoryBottomSheet()
            categoryBottomSheet.show(parentFragmentManager, "choose_category")
        }
        binding.btTypePortion.setOnClickListener {
            val portionTypeBottomSheet = SelectTypePortionBottomSheet()
            portionTypeBottomSheet.show(parentFragmentManager, "show_type_portions")
        }
        binding.ivPictureContainer.setOnClickListener { launcher.launch(viewModel.createCameraFile()) }
        binding.btSave.setOnClickListener {
            viewModel.saveMenuItem(
                name = binding.etName.getValue(),
                price = binding.etPrice.getValue(),
                portionSize = binding.etPortion.getValue()
            )
             findNavController().popBackStack()
        }
        lifecycleScope.launch {
            viewModel.chooseCategoryFlow.collect { category ->
                with(binding.btCategory) {
                    text = category.name
                    val categoryIcon = viewModel.getIconByCategory(category.icon)
                    setIconResource(categoryIcon)
                }
            }
        }
        lifecycleScope.launch { viewModel.savingError.collect { showToast(R.string.not_saving) } }
    }

    private fun setPhoto(path: String) {
        binding.ivPictureGallery.isVisible = false
        binding.tvAddPicture.isVisible = false
        Glide.with(binding.ivPictureContainer)
            .load(path)
            .into(binding.ivPictureContainer)
    }

    companion object {
        const val REQUEST_CATEGORY = "requestKeyCategory"
        const val BUNDLE_KEY_CATEGORY = "bundleKeyCategory"
        const val REQUEST_TYPE = "requestKeyType"
        const val BUNDLE_KEY_TYPE = "bundleKeyType"
    }
}

