package com.chama.chefcito_2.model

import com.google.firebase.firestore.DocumentId
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Recipe(
    val title: String = "",
    val image: String = "",
    val ingredients: ArrayList<String> = arrayListOf(),
    val liked: Int = 0,
    val saved: Int = 0,
    val steps: ArrayList<String> = arrayListOf(),
    val author: String = "Chefcito Team",
    @DocumentId val docId: String = ""
): Parcelable
