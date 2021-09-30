package com.misc.superheroapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Powerstats(
    @SerializedName("combat")
    val combat: String,
    @SerializedName("durability")
    val durability: String,
    @SerializedName("intelligence")
    val intelligence: String,
    @SerializedName("power")
    val power: String,
    @SerializedName("speed")
    val speed: String,
    @SerializedName("strength")
    val strength: String
) : Serializable