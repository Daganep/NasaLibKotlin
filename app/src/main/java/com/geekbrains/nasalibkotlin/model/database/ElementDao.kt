package com.geekbrains.nasalibkotlin.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekbrains.nasalibkotlin.model.entity.Element
import io.reactivex.Single

@Dao
interface ElementDao {
    @Query("SELECT * FROM table_elements")
    fun getAll(): Single<List<Element?>?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(elements: List<Element?>?): Single<List<Long?>?>?

    @Query("DELETE FROM table_elements")
    fun deleteAll(): Single<Int?>?
}