package com.gaur.laboratorio04.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gaur.laboratorio04.entities.BookEntity
import com.gaur.laboratorio04.entities.StudentEntity

@Dao
interface BookDao {
    @Query("select * from book")
    suspend fun getAll(): List<BookEntity>

    @Insert
    suspend fun insert(bookEntity: BookEntity)

    @Insert
    suspend fun insert(booksEntity: List<BookEntity>)

    @Delete
    suspend fun delete(bookEntity: BookEntity)
}