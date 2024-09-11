package com.example.gatopedia.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gatopedia.databinding.FragmentFavoritesBinding
import com.example.gatopedia.domain.viewmodel.HomeViewModel
import com.example.gatopedia.presentation.adapter.FavoriteAdapter
import com.example.gatopedia.presentation.viewmodel.SharedViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: FavoriteAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        sharedViewModel.favoriteBreeds.observe(viewLifecycleOwner) { favoriteBreeds ->
            adapter.updateData(favoriteBreeds)
        }
        adapter = FavoriteAdapter(emptyList()) { breedName ->
            removeFromFavorites(breedName)
        }
        binding.recyclerViewFav.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFav.adapter = adapter

        sharedViewModel.favoriteBreeds.observe(viewLifecycleOwner) { favoriteBreeds ->
            adapter.updateData(favoriteBreeds)
        }
        loadFavoriteBreeds()
    }

    private fun loadFavoriteBreeds() {
        val sharedPreferences = requireContext().getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val favoriteBreedNames = sharedPreferences.getStringSet("favoriteBreedNames", emptySet())?.toList() ?: emptyList()

        if (favoriteBreedNames.isNotEmpty()) {
            adapter.updateData(favoriteBreedNames)
        } else {
            adapter.updateData(emptyList())
            Toast.makeText(requireContext(), "Breed not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorites(breedName: String) {
        val sharedPreferences = requireContext().getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val favoriteBreedNames = sharedPreferences.getStringSet("favoriteBreedNames", mutableSetOf())?.toMutableSet()

        favoriteBreedNames?.remove(breedName)

        val editor = sharedPreferences.edit()
        editor.putStringSet("favoriteBreedNames", favoriteBreedNames)
        editor.apply()

        adapter.updateData(favoriteBreedNames?.toList() ?: emptyList())
    }

}