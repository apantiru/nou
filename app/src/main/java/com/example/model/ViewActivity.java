package com.example.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    Button button;

    private EditText emailText;
    private EditText firstTest;
    private EditText last_name_text;
    private EditText ageText;
    private EditText faculty_text;
    private Button go_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        emailText = findViewById(R.id.email);
        firstTest = findViewById(R.id.first_name);
        last_name_text = findViewById(R.id.last_name);
        ageText = findViewById(R.id.age);
        faculty_text = findViewById(R.id.faculty);
        AppDatabase appDatabase = Room.databaseBuilder(
                this,
                AppDatabase.class,
                "students_database"
        ).allowMainThreadQueries().build();
        button = findViewById(R.id.add_button);
        Handler handler = new Handler();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {


                                Bundle bundle = new Bundle();
                                bundle.putString("last_nname", last_name_text.getText().toString());
                                bundle.putString("first_name", firstTest.getText().toString());
//                                Integer.parseInt(ageText.getText().toString())
                                bundle.putString("age", ageText.getText().toString());

                                bundle.putString("faculty", faculty_text.getText().toString());
                                bundle.putString("email", emailText.getText().toString());

                                if (ageText.getText().toString().isEmpty() || emailText.getText().toString().isEmpty()) {
                                    Toast.makeText(getApplicationContext(),"Toate campurile trebuie completate!", Toast.LENGTH_LONG).show();
                                }
                                else if (Integer.getInteger(ageText.getText().toString()) < 0 && Integer.getInteger(ageText.getText().toString()) > 100)
                                    Toast.makeText(getApplicationContext(),"Varsta introdusa nu este buna", Toast.LENGTH_LONG).show();
                                else if( emailText.getText().toString().contains("@") != true ) {
                                    Toast.makeText(getApplicationContext(),"Email introdus invalid", Toast.LENGTH_LONG).show();
                                }
                                else if (faculty_text.getText().toString().isEmpty() || last_name_text.getText().toString().isEmpty() || firstTest.getText().toString().isEmpty() )
                                    Toast.makeText(getApplicationContext(),"Toate campurile trebuie completate!", Toast.LENGTH_LONG).show();
                                else
                                {
                                    Student student = new Student(last_name_text.getText().toString(), firstTest.getText().toString(), Integer.parseInt(ageText.getText().toString()), faculty_text.getText().toString(), emailText.getText().toString());
                                    Student[] students = new Student[] {student};
                                    appDatabase.userDao().insertAll(students);
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }




                            }
                        });
                    }
                });

                thread.start();

            }
        });
    }


}
