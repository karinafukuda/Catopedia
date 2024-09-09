package com.example.gatopedia.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gatopedia.R
import com.example.gatopedia.domain.viewmodel.HomeViewModel

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class BreedDetailsFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breed_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val catId = arguments?.getString("catId")
        if (catId != null) {
            // Use o catId para buscar os detalhes da raça e atualizar a interface
            viewModel.fetchBreedDetails(catId)
        } else {
            // Lidar com o caso em que o ID não foi passado corretamente
        }

    }

}