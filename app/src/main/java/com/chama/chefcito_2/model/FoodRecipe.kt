package com.chama.chefcito_2.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FoodRecipe (
    val id: Int,
    val title: String,
    val likes: Int,
    val saves: Int,
    val time: Int,
    val image: String ): Parcelable