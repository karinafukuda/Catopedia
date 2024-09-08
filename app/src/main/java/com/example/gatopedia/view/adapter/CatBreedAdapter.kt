package com.example.gatopedia.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gatopedia.R
import com.example.gatopedia.model.CatInfo
import com.squareup.picasso.Picasso

class CatBreedAdapter(
    private val context: Context,
    private var catImages: List<CatInfo>
) : RecyclerView.Adapter<CatBreedAdapter.CatBreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat_breed, parent, false)
        return CatBreedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) {
        val catImage = catImages[position]

        configureImage(catImage, holder)

        // TO-DO
        for (breed in catImage.breeds) {
            holder.breedNameTextView.text = breed.name
            holder.breedDescriptionTextView.text = breed.temperament

            // Se houver mais de uma raça, adicione um separador visual
            if (catImage.breeds.size > 1) {
                holder.breedDescriptionTextView.append("\n---\n")
            }
        }

        // Se não houver raças, exiba a mensagem padrão
        if (catImage.breeds.isEmpty()) {
            holder.breedNameTextView.text = context.getString(R.string.breed_not_found)
            holder.breedDescriptionTextView.text = ""
        }
    }

    private fun configureImage(
        catImage: CatInfo,
        holder: CatBreedViewHolder
    ) {
        Picasso.get()
            .load(catImage.url)
            .into(holder.catImageView)
    }

    override fun getItemCount() = catImages.size

    fun updateData(newCatImages: List<CatInfo>) {
        catImages = newCatImages
        notifyDataSetChanged()
    }

    class CatBreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val catImageView: ImageView = itemView.findViewById(R.id.cat_image)
        val breedNameTextView: TextView = itemView.findViewById(R.id.breed_name)
        val breedDescriptionTextView: TextView = itemView.findViewById(R.id.breed_description)
    }

    //        Log.d("API_DATA", "catImage: $catImage")
    //        Log.d("API_DATA", "catImage.breeds: ${catImage.breeds}")
}
