package com.example.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="students")
public class Student {



        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "lastname")
        private String lastname;

        @ColumnInfo(name = "firstname")
        private String firstname;

        @ColumnInfo(name = "age")
        private int age;

        @ColumnInfo(name = "faculty")
        private String faculty;

        @ColumnInfo(name = "email")
        private String email;

        public Student(String lastname, String firstname, int age, String faculty, String email) {
            this.lastname = lastname;
            this.firstname = firstname;
            this.age = age;
            this.faculty = faculty;
            this.email = email;
        }

        public Student(){}

        @Override
        public String toString() {
            return "Student{" +
                    "id='" + id+"\'" +
                    ", lastname='" + lastname + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", age='" + age + '\'' +
                    ", faculty='" + faculty + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }



        public void setEmail(String email) {
            this.email = email;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public String getFaculty() {
            return faculty;
        }

        public String getFirstname() {
            return firstname;
        }

        public int getAge() {
            return age;
        }

        public String getLastname() {
            return lastname;
        }
    }

