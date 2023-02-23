package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etName,etPhone;
    Button btAdd;
    Spinner spHome;
    RadioButton rbGender0,rbGender1;
    RadioGroup rg;
    ListView l;
    CheckBox cb1,cb2,cb3;
    String inFo1 = "Vu Van Dat - 0123456789 - Nam - Sơn La";
    String inFo2 = "Phan Thu Huong - 0256731889 - Nữ - Hải Phòng";
    ArrayList<String> listInfor;
    ArrayList<String> listHome;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapterQue;
    String hometown = "";
    String gender;
    String music, sport, game;

    private void init(){
        etName = findViewById(R.id.editTextTextPersonName);
        etPhone = findViewById(R.id.editTextPhone);
        spHome = findViewById(R.id.spinner);
        rbGender1 = findViewById(R.id.radioButton);
        rbGender0 = findViewById(R.id.radioButton2);
        btAdd = findViewById(R.id.button);
        rg = findViewById(R.id.radioGroup);
        cb1 = findViewById(R.id.checkBox4);
        cb2 = findViewById(R.id.checkBox5);
        cb3 = findViewById(R.id.checkBox6);
        listInfor = new ArrayList<>();
        listHome = new ArrayList<>();
        listInfor.add(inFo1);
        listInfor.add(inFo2);
        l = findViewById(R.id.listView);
        listHome.add("Hà Nội");
        listHome.add("Sơn La");
        listHome.add("Hải Phòng");
        listHome.add("Lào Cai");
        listHome.add("Ninh Bình");
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        spHome.setOnItemSelectedListener(this);
        arrayAdapterQue = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                listHome);
        spHome.setAdapter(arrayAdapterQue);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                listInfor);
        l.setAdapter(arrayAdapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder infor = new StringBuilder();
                String name = etName.getText().toString().trim();
                etName.setText("");
                String phone = etPhone.getText().toString().trim();
                etPhone.setText("");
                if(rbGender0.isChecked()){
                    gender = rbGender0.getText().toString();
                    rbGender0.setChecked(false);
                }
                if (rbGender1.isChecked()){
                    gender = rbGender1.getText().toString();
                    rbGender1.setChecked(false);
                }
                infor.append(name + " - ");
                infor.append(phone + " - ");
                infor.append(gender + " - ");
                infor.append(hometown + " - ");
                if(cb1.isChecked() && cb2.isChecked() && cb3.isChecked()){
                    music = cb1.getText().toString();
                    sport = cb2.getText().toString();
                    game = cb3.getText().toString();
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    infor.append(music + " - " +sport + " - " + game );
                }
                else if(cb2.isChecked() && cb3.isChecked()){
                    sport = cb2.getText().toString();
                    game = cb3.getText().toString();
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    infor.append(sport + " - " + game );
                }
                else if(cb1.isChecked() && cb3.isChecked()){
                    music = cb1.getText().toString();
                    game = cb3.getText().toString();
                    infor.append(music + " - " + game );
                }
                else if(cb1.isChecked() && cb2.isChecked()){
                    music = cb1.getText().toString();
                    sport = cb2.getText().toString();
                    infor.append(music + " - " + sport);
                }
                else if (cb1.isChecked()){
                    music = cb1.getText().toString();
                    cb1.setChecked(false);
                    infor.append(music );
                }
                else if (cb2.isChecked()){
                    sport = cb2.getText().toString();
                    cb2.setChecked(false);
                    infor.append(sport );
                }
                else if (cb3.isChecked()){
                    game = cb3.getText().toString();
                    cb3.setChecked(false);
                    infor.append(game );
                }
                listInfor.add(infor.toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        hometown = listHome.get(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}