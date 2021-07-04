package com.example.model;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button see_button;
    public class CustomComparator implements Comparator<Student> {


        @Override
        public int compare(Student o1, Student o2) {
            return o1.getAge() - o2.getAge();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        see_button = findViewById(R.id.new_button);
        Button gogo = findViewById(R.id.go_to_spinner);
        gogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SpinnerActivity.class);
                startActivity(intent);
            }
        });

        ListView lst= (ListView) findViewById(R.id.list_view);

        AppDatabase appDatabase = Room.databaseBuilder(
                this,
                AppDatabase.class,
                "students_database"
        ).allowMainThreadQueries().build();

        final List<Student> students = appDatabase.userDao().getAll();

        ArrayAdapter<Student> arrayadapter=new ArrayAdapter<Student>(this,android.R.layout.simple_list_item_1,students);
        lst.setAdapter(arrayadapter);

        Button sort_button = findViewById(R.id.sort_button);
        Button reset = findViewById(R.id.reset_but);
       ArrayAdapter<Student> finalStudents1 = arrayadapter;
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lst.setAdapter(arrayadapter);
            }
        });
        List<Student> finalStudents = students;
        sort_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                finalStudents1.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getAge() - o2.getAge();
                    }
                });
                lst.setAdapter(finalStudents1);
            }
        });



        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String selectedFromList = (lst.getItemAtPosition(position).toString());
                        String[] ids = selectedFromList.split("'");
                        String id1 = ids[1].split("=")[0];
                        appDatabase.userDao().deleteStudent(Integer.parseInt(id1));
                        lst.removeViewAt(position);
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.show();


                return false;
            }
        });
        see_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(intent);
            }
        });


    }
}