package com.geekbrains.nasalibkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MetaData(
    @Expose
    @field:SerializedName("total_hits")val total_hit: Int
)