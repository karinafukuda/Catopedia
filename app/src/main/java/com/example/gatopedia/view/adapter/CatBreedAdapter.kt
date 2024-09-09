package com.example.gatopedia.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.model.CatInfo
import com.example.gatopedia.view.viewholder.CatBreedViewHolder

class CatBreedAdapter(
    private var catImages: List<CatInfo>
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

    fun updateData(newCatImages: List<CatInfo>) {
        catImages = newCatImages
        notifyDataSetChanged()
    }
}

