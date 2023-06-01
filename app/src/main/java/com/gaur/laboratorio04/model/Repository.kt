package com.gaur.laboratorio04.model

import com.gaur.laboratorio04.entities.BookEntity
import com.gaur.laboratorio04.entities.StudentEntity
import com.gaur.laboratorio04.entities.StudentWithBooks

class Repository(private val appDatabase: AppDatabase) {

    suspend fun getAllStudents(): List<StudentEntity> {
        return appDatabase.studentDao().getAll()
    }

    suspend fun getStudentWithBooks(): List<StudentWithBooks> {
        return appDatabase.studentDao().getStudentWithBooks()
    }

    suspend fun insertStudents(studentsEntity: List<StudentEntity>) {
        appDatabase.studentDao().insert(studentsEntity)
    }

    suspend fun insertBooks(books: List<BookEntity>) {
        appDatabase.bookDao().insert(books)
    }

    suspend fun insertBook(book: BookEntity) {
        appDatabase.bookDao().insert(book)
    }

    suspend fun getAllBooks(): List<BookEntity> {
        return appDatabase.bookDao().getAll()
    }
}