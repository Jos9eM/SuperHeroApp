package com.misc.superheroapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HeroInfoResponse(
    @SerializedName("response")
    val response: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("appearance")
    val appearance: Appearance,
    @SerializedName("biography")
    val biography: Biography,
    @SerializedName("connections")
    val connections: Connections,
    @SerializedName("image")
    val image: Image,
    @SerializedName("powerstats")
    val powerstats: Powerstats,
    @SerializedName("work")
    val work: Work
) : Serializable