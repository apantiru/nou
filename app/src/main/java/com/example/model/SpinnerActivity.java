package com.example.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SpinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Spinner spin = (Spinner) findViewById(R.id.spinner);

        String[] values = {"id", "age", "firstname", "lastname", "faculty", "email"};

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,values);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        AppDatabase appDatabase = Room.databaseBuilder(
                this,
                AppDatabase.class,
                "students_database"
        ).allowMainThreadQueries().build();

        ListView lst= (ListView) findViewById(R.id.listi);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String selected = (String) spin.getSelectedItem();
                final List<Student> students = appDatabase.userDao().getSpecificStudents(selected, s.toString());
                ArrayAdapter<Student> arrayadapter=new ArrayAdapter<Student>(getApplicationContext(),android.R.layout.simple_list_item_1,students);
                lst.setAdapter(arrayadapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 3) {
                    String selected = (String) spin.getSelectedItem();
                    final List<Student> students = appDatabase.userDao().getSpecificStudents(selected, s.toString());
                    ArrayAdapter<Student> arrayadapter=new ArrayAdapter<Student>(getApplicationContext(),android.R.layout.simple_list_item_1,students);
                    lst.setAdapter(arrayadapter);
                }
            }
        };

        EditText editText = findViewById(R.id.search);
        editText.addTextChangedListener(textWatcher);
    }
}