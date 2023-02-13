package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddUser extends AppCompatActivity {
    EditText etIDAddUser, etNameAddUser, etPhoneAddUser;
    Button btnOK, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        etIDAddUser = findViewById(R.id.etIdAddUser);
        etNameAddUser = findViewById(R.id.etNameAddUser);
        etPhoneAddUser = findViewById(R.id.etPhoneAddUser);
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            int id = bundle.getInt("Id");
            String name = bundle.getString("Name");
            String phone = bundle.getString("Phone");
            etIDAddUser.setText(String.valueOf(id));
            etNameAddUser.setText(name);
            etPhoneAddUser.setText(phone);
            btnOK.setText("Edit");
        }
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tạo intent để trở về main
                Intent intent = new Intent();
                // Tạo bundle là đối tượng để chứa dữ liệu
                Bundle bundle = new Bundle();
                bundle.putInt("Id", Integer.parseInt(etIDAddUser.getText().toString()));
                bundle.putString("Name", etNameAddUser.getText().toString());
                bundle.putString("Phone", etPhoneAddUser.getText().toString());
                //Bật bundle lên intent
                intent.putExtras(bundle);
                //Trả về bằng hàm set result
                setResult(200, intent);
                finish();
            }
        });
    }

}
