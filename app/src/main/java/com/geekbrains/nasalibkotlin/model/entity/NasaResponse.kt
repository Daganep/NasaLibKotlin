package com.geekbrains.nasalibkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NasaResponse (
    @Expose
    @field:SerializedName("collection")val collection: Collection?
)