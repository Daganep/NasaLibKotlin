package com.geekbrains.nasalibkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item (
    @Expose
    @field:SerializedName("links")val links: List<ItemLinks>?,
    @Expose
    @field:SerializedName("data")val data: List<ItemData>?,
    @Expose
    @field:SerializedName("href")val href: String?
    )