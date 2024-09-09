package com.example.gatopedia.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.data.CatInformation
import com.example.gatopedia.presentation.viewholder.CatBreedViewHolder

class CatBreedAdapter(
    private var catImages: List<CatInformation>
) : RecyclerView.Adapter<CatBreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat_breed, parent, false)
        return CatBreedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) {
        val catImage = catImages[position]
        holder.configureImage(catImage, holder)
        holder.configureTexts(catImage, holder)
    }

    override fun getItemCount() = catImages.size

    fun updateData(newCatImages: List<CatInformation>) {
        catImages = newCatImages
        notifyDataSetChanged()
    }
}

