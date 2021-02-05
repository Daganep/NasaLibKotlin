package com.geekbrains.nasalibkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Collection (
        @Expose
        @field:SerializedName("links")val links: List<Link>?,
        @Expose
        @field:SerializedName("items")val items: List<Item>?,
        @Expose
        @field:SerializedName("metadata")val metadta: MetaData?,
        @Expose
        @field:SerializedName("href")val href: String?,
        @Expose
        @field:SerializedName("version")val version: String?
    )