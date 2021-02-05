package com.geekbrains.nasalibkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Link (
    @Expose
    @field:SerializedName("href")val href: String?
)