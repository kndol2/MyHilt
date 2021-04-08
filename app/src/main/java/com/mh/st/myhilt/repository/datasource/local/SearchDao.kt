package com.mh.st.myhilt.repository.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchDao {
    @Query("SELECT * FROM search_table WHERE keyword = :keyword")
    fun getSearchData(keyword: String): PagingSource<Int, SearchEntity>

    @Query("SELECT * FROM search_table WHERE keyword = :keyword")
    fun getLatestPost(keyword: String): List<SearchEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchData(data: SearchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<SearchEntity>)

    @Query("DELETE FROM search_table WHERE keyword = :keyword")
    fun deleteSearchData(keyword: String)

    @Query("DELETE FROM search_table")
    fun deleteAll()
}
