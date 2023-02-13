package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> ContactList;
    private Adapter ListAdapter;
    private EditText etSearch;
    private ListView lstContact;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInsstanceState){
        super.onCreate(savedInsstanceState);
        setContentView(R.layout.activity_main);
        ContactList = new ArrayList<>();
        ContactList.add(new Contact(1,"img1","Nguyễn Văn An","56789065"));
        ContactList.add(new Contact(2,"img2","Trần Thị Bích","4567899"));
        ContactList.add(new Contact(3,"img3","Mai Thu Hà","92349472"));
        ListAdapter = new Adapter(ContactList, this);
        etSearch = findViewById(R.id.etSearch);
        lstContact = findViewById(R.id.lstContact);
        btnAdd = findViewById(R.id.btnAdd);
        lstContact.setAdapter(ListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuSortName:
                break;
            case R.id.mnuSortPhone:
                break;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnuSort:
                Toast.makeText(MainActivity.this, "Sort",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnuAdd:
                //Tạo đối tượng Intent để gọi tới AddNew
                Intent intent = new Intent(MainActivity.this,
                        AddUser.class);
                //Có 2 cacash gọi tới AddNew
                //Gọi không cần chờ dữ liệu phản hồi dùng hàm startActivity
                //Gọi nhưng chờ dữ liệu phản hồi dùng hàm startActivityForResult
                //Trường hợp này muốn chờ dữ liệu Contact mới nên chọn cách 2
                //tham số thứ nhất là intent
                //tham số thứ hai là requestCode để đánh dấu lần gọi
                startActivityForResult(intent, 100);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.optionmenusortadd, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnuEdit:
                //Tạo đối tượng Intent để gọi tới AddNew
                Intent intent = new Intent(MainActivity.this,
                        AddUser.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id", listUser.get(selectedid).getId());
                bundle.putString("Name", listUser.get(selectedid).getName());
                bundle.putString("Phone", listUser.get(selectedid).getPhoneNum());
                intent.putExtras(bundle);
                startActivityForResult(intent, 200);
                break;
            case R.id.mnuDelete:
                Toast.makeText(this,
                        "Delete: " + listUser.get(selectedid).getName(),
                        Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}