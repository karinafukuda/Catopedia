package com.example.gatopedia.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.data.CatInformation
import com.example.gatopedia.presentation.viewholder.FavoriteViewHolder

class FavoriteAdapter (
    private var catImages: List<CatInformation>
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = catImages.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}