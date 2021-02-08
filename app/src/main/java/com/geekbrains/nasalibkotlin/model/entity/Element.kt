package com.geekbrains.nasalibkotlin.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_elements")
class Element() : Serializable{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var URL: String? = null
    var title: String? = null
    var date: String? = null
    var creator: String? = null

    constructor(item: Item) : this() {
        item.links?.get(0)?.href.let { this.URL = it }
        item.data?.get(0)?.title.let { this.title = it }
        item.data?.get(0)?.dateCreate.let { this.date = it }
        item.data?.get(0)?.creator.let { this.creator = it }
    }

}