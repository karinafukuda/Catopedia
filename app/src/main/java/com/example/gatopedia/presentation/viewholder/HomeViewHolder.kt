package com.example.gatopedia.presentation.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.data.CatInformation
import com.squareup.picasso.Picasso

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val catImageView: ImageView = itemView.findViewById(R.id.cat_image)
    private val breedNameTextView: TextView = itemView.findViewById(R.id.breed_name)
    val moreInfoTextView: TextView = itemView.findViewById(R.id.more_info)

    fun configureTexts(
        catImage: CatInformation,
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
        catImage: CatInformation,
        holder: HomeViewHolder
    ) {
        Picasso.get()
            .load(catImage.url)
            .placeholder(R.drawable.ic_image_default)
            .error(R.drawable.ic_image_default)
            .into(holder.catImageView)
    }
}