package com.misc.superheroapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Connections(
    @SerializedName("group-affiliation")
    val groupAffiliation: String,
    @SerializedName("relatives")
    val relatives: String
): Serializable