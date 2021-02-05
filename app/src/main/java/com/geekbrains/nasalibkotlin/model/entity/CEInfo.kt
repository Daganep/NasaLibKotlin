package com.geekbrains.nasalibkotlin.model.entity

import java.io.Serializable

class CEInfo(item: Item) : Serializable{
    val URL: String?
    val title: String?
    val date: String?
    val creator: String?
    init{
        item.links?.get(0)?.href.let { URL = it }
        item.data?.get(0)?.title.let { title = it }
        item.data?.get(0)?.dateCreate.let { date = it }
        item.data?.get(0)?.creator.let { creator = it }
    }

}