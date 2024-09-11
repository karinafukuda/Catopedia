package com.example.gatopedia.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class CatInformation(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("breeds") val breeds: List<Breed>
) : Serializable, Parcelable {

    @Parcelize
    data class Breed(
        @SerializedName("weight") val weight: Weight,
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("temperament") val temperament: String,
        @SerializedName("origin") val origin: String,
        @SerializedName("country_codes") val countryCodes: String,
        @SerializedName("country_code") val countryCode: String,
        @SerializedName("life_span") val lifeSpan: String,
        @SerializedName("wikipedia_url") val wikipediaUrl: String
    ) : Parcelable

    @Parcelize
    data class Weight(
        @SerializedName("imperial") val imperial: String,
        @SerializedName("metric") val metric: String
    ) : Parcelable
}
