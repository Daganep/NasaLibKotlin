package com.geekbrains.nasalibkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemData(
    @Expose
    @field:SerializedName("title")val title: String?,
    @Expose
    @field:SerializedName("description")val description: String?,
    @Expose
    @field:SerializedName("secondary_creator")val creator: String?,
    @Expose
    @field:SerializedName("date_created")val dateCreate: String?
)