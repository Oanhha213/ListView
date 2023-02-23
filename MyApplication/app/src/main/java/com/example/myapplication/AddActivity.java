package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    private EditText etHoten,etSdt;
    private Button btAdd;
    private Spinner spHome;
    private RadioButton rbGender0,rbGender1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }
    private void initView(){
        etHoten = findViewById(R.id.editTextTextPersonName);
        etSdt = findViewById(R.id.editTextPhone);
        rbGender1 = findViewById(R.id.radioButton);
        rbGender0 = findViewById(R.id.radioButton2);
    }
    public void addTT(){

    }

}