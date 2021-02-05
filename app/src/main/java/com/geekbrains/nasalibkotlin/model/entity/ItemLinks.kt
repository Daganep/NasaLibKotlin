package com.geekbrains.nasalibkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemLinks (
    @Expose
    @field:SerializedName("href")val href: String?
)