package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import android.Manifest
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import com.example.domain.enteties.advertisement.DomainAdvertisementCategory
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentCreatingAdvertisementBinding
import com.example.upyourpartyandroid.ui.Utils.argumentsNullableLong
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.create.recycler.ImagesAdapter
import com.example.upyourpartyandroid.ui.fragments.registration.RegistrationFragment
import com.example.upyourpartyandroid.ui.helpers.ImagePickHelper
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import com.example.upyourpartyandroid.ui.views.ViewUtils.showError
import com.redmadrobot.inputmask.MaskedTextChangedListener
import javax.inject.Inject

class CreatingAdvertisementsFragment :
    BaseRequestFragment<FragmentCreatingAdvertisementBinding, CreatingAdvertisementsViewModel>(
        CreatingAdvertisementsViewModel::class,
        FragmentCreatingAdvertisementBinding::inflate
    ) {

    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    private val imagePickHelper by lazy { ImagePickHelper(requireActivity().activityResultRegistry) }

    var advertisementId: Long? by argumentsNullableLong()

    override fun onResume() {
        binding.recyclerImages.adapter = imagesAdapter
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupViews()
        this.setupRecycler()
        this.setupListeners()
        viewModel.prepare(advertisementId)
        viewModel.observe(this, ::render, ::handleSideEffect)
    }

    private fun setupRecycler() {
        val categoriesItems = listOf(
            getString(R.string.advertisements_category_birthday),
            getString(R.string.advertisements_category_party),
            getString(R.string.advertisements_category_wedding),
            getString(R.string.advertisements_category_corporate),
        )
        binding.categorySpinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.view_layout_spinner_item,
            categoriesItems
        )
        binding.recyclerImages.adapter = imagesAdapter
    }

    private fun setupViews() {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        lifecycle.addObserver(imagePickHelper)
        MaskedTextChangedListener.installOn(
            binding.phoneNumberAddAnnounce,
            RegistrationFragment.RUSSIAN_MASK_FORMAT
        )
    }

    private fun render(state: CreatingAdvertisementsState) = with(binding) {
        state.announce?.let { advertisement ->
            addAnnounceDescription.setText(advertisement.description)
            addAnnounceName.setText(advertisement.title)
            priceAddAnnounce.setText(advertisement.price.toString())
            city.setText(advertisement.city)
            setSelectedCategory(advertisement.category)
        }
        imagesAdapter.setList(state.images)
    }

    private fun setSelectedCategory(category: DomainAdvertisementCategory) {
        val position = when (category) {
            DomainAdvertisementCategory.BIRTHDAY -> DomainAdvertisementCategory.BIRTHDAY.ordinal
            DomainAdvertisementCategory.WEDDING -> DomainAdvertisementCategory.WEDDING.ordinal
            DomainAdvertisementCategory.PARTY -> DomainAdvertisementCategory.PARTY.ordinal
            DomainAdvertisementCategory.CORPORATE -> DomainAdvertisementCategory.CORPORATE.ordinal

        }
        binding.categorySpinner.setSelection(position)
    }

    private fun handleSideEffect(sideEffects: BaseSideEffects) = with(binding) {
        when(sideEffects) {

            is BaseSideEffects.ShowLoadingIndicator -> showLoadingIndicator()

            is BaseSideEffects.HideLoadingIndicator -> hideLoadingIndicator()

            is CreatingAdvertisementsSideEffects.CityInvalid ->
                city.showError(R.string.create_announce_city_invalid_message)

            is CreatingAdvertisementsSideEffects.NameInvalid ->
                addAnnounceName.showError(R.string.create_announce_name_invalid_message)

            is CreatingAdvertisementsSideEffects.PhoneInvalid ->
                phoneNumberAddAnnounce.showError(R.string.create_announce_phone_invalid_message)

            is CreatingAdvertisementsSideEffects.DescriptionInvalid ->
                addAnnounceDescription.showError(R.string.create_announce_description_invalid_message)

            is CreatingAdvertisementsSideEffects.PriceInvalid ->
                priceAddAnnounce.showError(R.string.create_announce_price_invalid_message)

        }
    }

    private fun openImageSelection(position: Int) {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
        imagePickHelper.setOnResultListener { imageUri ->
            viewModel.handleImagePick(imageUri, position)
        }
    }

    private fun setupListeners() {
        imagesAdapter.onHolderClick = ::openImageSelection
        binding.backBtn.setClickListener(viewModel::onBackClick)
        binding.btnAddAdvertisement.setClickListener(::onAddClick)
    }

    private fun onAddClick() = with(binding) {
        val phone = phoneNumberAddAnnounce.text.toString()
        val description = addAnnounceDescription.text.toString()
        val title = addAnnounceName.text.toString()
        val category = DomainAdvertisementCategory.values()[categorySpinner.selectedItemPosition]
        val city = city.text.toString()
        val price = priceAddAnnounce.text.toString()
        viewModel.createAdvertisement(price, description, phone, city, category, title)
    }

    override fun onStop() {
        binding.recyclerImages.adapter = null
        super.onStop()
    }

}
