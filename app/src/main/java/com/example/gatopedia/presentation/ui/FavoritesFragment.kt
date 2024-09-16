package com.example.gatopedia.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gatopedia.databinding.FragmentFavoritesBinding
import com.example.gatopedia.presentation.adapter.FavoriteAdapter
import com.example.gatopedia.presentation.viewmodel.HomeViewModel
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

        adapter = FavoriteAdapter(emptyList()) { breedName ->
            sharedViewModel.removeFromFavorites(breedName)
        }

        sharedViewModel.favoriteBreeds.observe(viewLifecycleOwner) { favoriteBreeds ->
            adapter.updateData(favoriteBreeds)

            if (favoriteBreeds.isEmpty()) {
                binding.emptyListMessage.visibility = View.VISIBLE
                binding.recyclerViewFav.visibility = View.GONE
            } else {
                binding.emptyListMessage.visibility = View.GONE
                binding.recyclerViewFav.visibility = View.VISIBLE
            }
        }

        binding.recyclerViewFav.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFav.adapter = adapter
    }
}