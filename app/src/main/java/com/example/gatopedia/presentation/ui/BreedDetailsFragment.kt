package com.example.gatopedia.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gatopedia.R
import com.example.gatopedia.data.CatInformation
import com.example.gatopedia.databinding.FragmentBreedDetailsBinding
import com.example.gatopedia.domain.viewmodel.HomeViewModel
import com.example.gatopedia.util.LinkUtils
import com.squareup.picasso.Picasso

private const val CAT_BREED = "catBreed"
private const val IMAGE_URL = "imageUrl"

class BreedDetailsFragment : Fragment() {
    private var _binding: FragmentBreedDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        handleBundleReceiver()

    }

    private fun handleBundleReceiver() {
        val breed = arguments?.getParcelable<CatInformation.Breed>(CAT_BREED)
        val imageUrl = arguments?.getString(IMAGE_URL)

        breed?.let {
            with(binding.cardDetails) {
                name.text = it.name
                lifeSpan.text = it.lifeSpan
                origin.text = it.origin
                description.text = it.description
                temperament.text = it.temperament
                weight.text = it.weight.metric

                LinkUtils.makeLinkClickable(wiki,
                    getString(R.string.wikipedia_for_more_information), it.wikipediaUrl)
            }
        } ?: error("Objet Breed not found")

        imageUrl?.let {
            Picasso.get()
                .load(it)
                .placeholder(R.drawable.ic_image_default)
                .error(R.drawable.ic_image_default)
                .into(binding.cardDetails.catImage)
        } ?: error("Objet ImageUrl not found")
    }
}