package com.example.gatopedia.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gatopedia.R
import com.example.gatopedia.data.CatData
import com.example.gatopedia.databinding.FragmentHomeBinding
import com.example.gatopedia.presentation.adapter.HomeAdapter
import com.example.gatopedia.presentation.viewmodel.HomeViewModel
import com.example.gatopedia.presentation.viewmodel.SharedViewModel

private const val TWO_CARDS_IN_LINE = 2
private const val VALIDATION_CHAR = "The breed search must have exactly 4 characters"
private const val SIZE_ENTER_TEXT = 4

class HomeFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val adapter = configureAdapter()

        observeUpdateImage(adapter)
        observerError()
        handleSearchBreedByName()
        adapter.setOnItemClickListener(this)

        viewModel.fetchRandomCatList()
    }

    override fun onItemClick(cat: CatData) {
        getCatDataOnSelected(cat)
        findNavController().navigate(R.id.home_to_breed_detail)
    }

    private fun getCatDataOnSelected(cat: CatData) {
        sharedViewModel.setSelectedBreed(cat.breeds.firstOrNull())
        sharedViewModel.setSelectedBreedImageUrl(cat.url)
    }

    private fun handleSearchBreedByName() {
        binding.searchHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.length != SIZE_ENTER_TEXT) {
                        Toast.makeText(
                            requireContext(),
                            VALIDATION_CHAR,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else viewModel.fetchImagesByBreeds(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun observeUpdateImage(adapter: HomeAdapter) {
        viewModel.catImages.observe(viewLifecycleOwner) { newCatImages ->
            newCatImages?.let { adapter.updateData(it) }
        }
    }

    private fun configureAdapter(): HomeAdapter {
        val adapter = HomeAdapter(emptyList())
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), TWO_CARDS_IN_LINE)
        binding.recyclerView.adapter = adapter
        return adapter
    }

    private fun observerError() {
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            showError(errorMessage)
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    // Prevents memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}