package com.gaur.laboratorio04.model
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.gaur.laboratorio04.entities.StudentEntity
import com.gaur.laboratorio04.entities.StudentWithBooks

@Dao
interface StudentDao {
    @Query("select * from student")
    suspend fun getAll(): List<StudentEntity>

    @Transaction
    @Query("select * from student")
    suspend fun getStudentWithBooks(): List<StudentWithBooks>

    @Insert
    suspend fun insert(studentEntity: StudentEntity)

    @Insert
    suspend fun insert(studentsEntity: List<StudentEntity>)

    @Delete
    suspend fun delete(studentEntity: StudentEntity)
}