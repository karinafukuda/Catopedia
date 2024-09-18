package com.example.gatopedia.presentation.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.data.CatData
import com.squareup.picasso.Picasso

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val catImageView: ImageView = itemView.findViewById(R.id.cat_image)
    private val breedNameTextView: TextView = itemView.findViewById(R.id.breed_name)

    fun configureTexts(
        catImage: CatData,
        holder: HomeViewHolder
    ) {
        for (breed in catImage.breeds) {
            holder.breedNameTextView.text = breed.name
        }

        if (catImage.breeds.isEmpty()) {
            holder.breedNameTextView.text = itemView.context.getString(R.string.breed_not_found)
        }
    }

    fun configureImage(
        catImage: CatData,
        holder: HomeViewHolder
    ) {
        Picasso.get()
            .load(catImage.url)
            .placeholder(R.drawable.ic_image_default)
            .error(R.drawable.ic_image_default)
            .into(holder.catImageView)
    }
}