package com.example.gatopedia.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.data.CatInformation
import com.example.gatopedia.presentation.ui.BreedDetailsFragment
import com.example.gatopedia.presentation.ui.OnItemClickListener
import com.example.gatopedia.presentation.viewholder.HomeViewHolder

class HomeAdapter(
    private var catImages: List<CatInformation>
) : RecyclerView.Adapter<HomeViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat_breed, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val catImage = catImages[position]
        holder.configureImage(catImage, holder)
        holder.configureTexts(catImage, holder)
    }

    override fun getItemCount() = catImages.size

    fun updateData(newCatImages: List<CatInformation>) {
        catImages = newCatImages
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}

