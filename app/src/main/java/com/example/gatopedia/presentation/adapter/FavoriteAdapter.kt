package com.example.gatopedia.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.presentation.viewholder.FavoriteViewHolder

class FavoriteAdapter(
    private var favoriteBreeds: List<String>,
    private val onFavoriteClick: (String) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_favorites, parent, false)
        return FavoriteViewHolder(itemView)
    }

    // WIP
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val breedName = favoriteBreeds[position]
        holder.breedNameTextView.text = breedName
        holder.breedIcon.setImageResource(R.drawable.ic_fav_selected)
        holder.breedIcon.setOnClickListener {
            onFavoriteClick(breedName)
        }
    }

    override fun getItemCount(): Int = favoriteBreeds.size

    fun updateData(newFavoriteBreeds: List<String>) {
        favoriteBreeds = newFavoriteBreeds
        notifyDataSetChanged()
    }
}