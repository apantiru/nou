package com.example.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertAll(Student ...students);

    @Insert
    void insertSingleStudent(Student student);

    @Delete
    void delete(Student student);

    @Query("delete from students where id = :id1")
    void deleteStudent(int id1);

    @Query("select * from students where :column_name = :value")
    List<Student> getSpecificStudents(String column_name, String value);

    @Query("SELECT * FROM students")
    List<Student> getAll();
}
