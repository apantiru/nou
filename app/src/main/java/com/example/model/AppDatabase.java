package com.example.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Student.class})
abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao userDao();

}