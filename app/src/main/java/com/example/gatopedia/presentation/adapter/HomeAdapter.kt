package com.example.gatopedia.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.data.CatData
import com.example.gatopedia.presentation.ui.OnItemClickListener
import com.example.gatopedia.presentation.viewholder.HomeViewHolder

private const val BREED_NOT_FOUND = "Breed not found"

class HomeAdapter(
    private var catImages: List<CatData>
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

        holder.itemView.setOnClickListener {
            if (catImage.breeds.isNotEmpty()) {
                onItemClickListener?.onItemClick(catImage)
            } else Toast.makeText(holder.itemView.context, BREED_NOT_FOUND, Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getItemCount() = catImages.size

    fun updateData(newCatImages: List<CatData>) {
        catImages = newCatImages
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}

