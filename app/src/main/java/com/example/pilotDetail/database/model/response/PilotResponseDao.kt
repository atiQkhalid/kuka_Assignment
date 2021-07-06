package com.example.pilotDetail.database.model.response

import com.google.gson.annotations.SerializedName

data class PilotResponseDao(

    @field:SerializedName("items")
    val items: List<ItemsItem>? = null

)

data class ItemsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("point")
    val point: Int? = null
)