package com.example.gatopedia.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R

class FavoriteAdapter(
    private var favoriteBreeds: List<String>,
    private val onRemoveFavorite: (String) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_favorites, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val breedName = favoriteBreeds[position]
        holder.bind(breedName)
    }

    override fun getItemCount(): Int = favoriteBreeds.size

    fun updateData(newFavoriteBreeds: List<String>) {
        favoriteBreeds = newFavoriteBreeds
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val breedNameTextView: TextView = itemView.findViewById(R.id.name_favorite)
        private val breedIcon: ImageButton = itemView.findViewById(R.id.favorite_button)

        internal fun bind(breedName: String) {
            breedNameTextView.text = breedName
            breedIcon.setOnClickListener {
                onRemoveFavorite(breedName)
            }
        }
    }
}