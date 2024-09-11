package com.example.gatopedia.presentation.viewholder

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val breedNameTextView: TextView = itemView.findViewById(R.id.name_favorite)
    val breedIcon: ImageButton = itemView.findViewById(R.id.favorite_button)

}