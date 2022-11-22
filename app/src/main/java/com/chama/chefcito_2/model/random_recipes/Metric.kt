package com.chama.chefcito_2.model.random_recipes

import com.google.gson.annotations.SerializedName

data class Metric (

    @SerializedName("amount"    ) var amount    : Float?    = null,
    @SerializedName("unitShort" ) var unitShort : String? = null,
    @SerializedName("unitLong"  ) var unitLong  : String? = null

)