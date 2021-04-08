package com.mh.st.myhilt.repository.datasource.local

import androidx.room.Entity
import com.mh.st.myhilt.repository.model.Documents

@Entity(tableName = "search_table", primaryKeys = ["thumbnail_url"])
data class SearchEntity(
    val keyword: String,
    val collection: String,
    val thumbnail_url: String,
    val image_url: String,
    val width: Int,
    val height: Int,
    val display_sitename: String,
    val doc_url: String,
    val datetime: String
)

fun List<Documents>.asDatabaseModel(keyword: String): List<SearchEntity> {
    return map {
        SearchEntity(
            keyword = keyword,
            collection = it.collection,
            thumbnail_url = it.thumbnail_url,
            image_url = it.image_url,
            width = it.width,
            height = it.height,
            display_sitename = it.display_sitename,
            doc_url = it.doc_url,
            datetime = it.datetime
        )
    }
}
