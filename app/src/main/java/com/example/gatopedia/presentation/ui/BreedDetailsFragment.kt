package com.example.gatopedia.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gatopedia.R
import com.example.gatopedia.databinding.FragmentBreedDetailsBinding
import com.example.gatopedia.presentation.viewmodel.HomeViewModel
import com.example.gatopedia.presentation.viewmodel.SharedViewModel
import com.example.gatopedia.util.LinkUtils
import com.squareup.picasso.Picasso

class BreedDetailsFragment : Fragment() {
    private var _binding: FragmentBreedDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var sharedViewModel: SharedViewModel

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
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        handleBreedDataSelected()
        goToMoreBreedDetails()
    }

    private fun goToMoreBreedDetails() {
        binding.cardDetails.favoriteButton.setOnClickListener {
            val breed = sharedViewModel.selectedBreed.value
            breed?.let {
                val breedName = breed.name
                saveFavoriteBreed(breedName)
                findNavController().navigate(R.id.breed_detail_to_favorites)
            }
        }
    }

    private fun handleBreedDataSelected() {
        sharedViewModel.selectedBreed.observe(viewLifecycleOwner) { breed ->
            breed?.let {
                with(binding.cardDetails) {
                    name.text = it.name
                    lifeSpan.text = it.lifeSpan
                    origin.text = it.origin
                    description.text = it.description
                    temperament.text = it.temperament
                    weight.text = it.weight.metric

                    LinkUtils.makeLinkClickable(
                        wiki,
                        getString(R.string.wikipedia_for_more_information), it.wikipediaUrl
                    )
                }
            }
        }

        sharedViewModel.selectedBreedImageUrl.observe(viewLifecycleOwner) { imageUrl ->
            imageUrl?.let {
                Picasso.get()
                    .load(it)
                    .placeholder(R.drawable.ic_image_default)
                    .error(R.drawable.ic_image_default)
                    .into(binding.cardDetails.catImage)
            }
        }
    }

    private fun saveFavoriteBreed(breedName: String) = sharedViewModel.addToFavorites(breedName)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}