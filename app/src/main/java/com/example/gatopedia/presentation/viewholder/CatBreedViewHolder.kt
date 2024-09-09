package com.example.gatopedia.presentation.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.data.CatInformation
import com.example.gatopedia.util.EMPTY
import com.squareup.picasso.Picasso

class CatBreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val catImageView: ImageView = itemView.findViewById(R.id.cat_image)
    private val breedNameTextView: TextView = itemView.findViewById(R.id.breed_name)
    private val breedDescriptionTextView: TextView = itemView.findViewById(R.id.breed_description)

    fun configureTexts(
        catImage: CatInformation,
        holder: CatBreedViewHolder
    ) {
        for (breed in catImage.breeds) {
            holder.breedNameTextView.text = breed.name
            holder.breedDescriptionTextView.text = breed.temperament

            if (catImage.breeds.size > 1) {
                holder.breedDescriptionTextView.append("\n---\n")
            }
        }

        if (catImage.breeds.isEmpty()) {
            holder.breedNameTextView.text = itemView.context.getString(R.string.breed_not_found)
            holder.breedDescriptionTextView.text = EMPTY
        }
    }

    fun configureImage(
        catImage: CatInformation,
        holder: CatBreedViewHolder
    ) {
        Picasso.get()
            .load(catImage.url)
            .into(holder.catImageView)
    }
}