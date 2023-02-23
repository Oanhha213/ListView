package com.example.listview;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> ContactList;
    private Adapter ListAdapter;
    private EditText etSearch;
    private ListView lstContact;
    private FloatingActionButton btnAdd;
    int SelectedItemId;

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
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.Tạo intent để mở sub
                Intent intent = new Intent(MainActivity.this,SubActivity1.class);
                //2.Truyền dữ liệu sang sub bằng bundle nếu cần
                //3. Mở sub bằng cách gọi hàm
                //startactivity hoặc startactivityforresult
                startActivityForResult(intent, 100);
            }
        });
        lstContact = findViewById(R.id.lstContact);
        lstContact.setAdapter(ListAdapter);
        registerForContextMenu(lstContact);
        lstContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SelectedItemId = i;
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.mnuSortName:
//                break;
//            case R.id.mnuSortPhone:
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.mnuSort:
//                Toast.makeText(MainActivity.this, "Sort",
//                        Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.mnuAdd:
//                //Tạo đối tượng Intent để gọi tới AddNew
//                Intent intent = new Intent(MainActivity.this,
//                        AddUser.class);
//                //Có 2 cacash gọi tới AddNew
//                //Gọi không cần chờ dữ liệu phản hồi dùng hàm startActivity
//                //Gọi nhưng chờ dữ liệu phản hồi dùng hàm startActivityForResult
//                //Trường hợp này muốn chờ dữ liệu Contact mới nên chọn cách 2
//                //tham số thứ nhất là intent
//                //tham số thứ hai là requestCode để đánh dấu lần gọi
//                startActivityForResult(intent, 100);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = new MenuInflater(this);
//        inflater.inflate(R.menu.optionmenusortadd, menu);
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.optionmenueditdelete,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Contact c = ContactList.get(SelectedItemId);
        switch (item.getItemId())
        {
            case R.id.mnuEdit:
                //Tạo đối tượng Intent để gọi tới Sub
                Intent intent = new Intent(MainActivity.this,
                        SubActivity1.class);
                //2. Truyền dữ liệu sang sub bằng bundle nếu cần

                Bundle b = new Bundle();
                b.putInt("Id", c.getId());
                b.putString("Image", c.getImages());
                b.putString("Name", c.getName());
                b.putString("Phone", c.getPhone());
                intent.putExtras(b);
                //3.Mở sub bằng cách gọi hàm
                //startactivity hoặc startactivityforresult
                startActivityForResult(intent, 200);
                break;
            case R.id.mnuDelete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Xóa?");
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactList.remove(SelectedItemId);
                        lstContact.setAdapter(ListAdapter);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.mnuCall:
                Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + c.getPhone()));
                startActivity(in);
                break;
            case R.id.mnuChat:
                Intent intent1 = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: " + c.getPhone()));
                intent1.putExtra("sms_body", "Nội dung tin nhắn");
                startActivity(intent1);
                break;
            case R.id.mnuEmail:
                Intent intent2 = new Intent(Intent.ACTION_SENDTO);
                intent2.setData(Uri.parse("mailto:"));

                try {
                    startActivity(intent2);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Ứng dụng Email không tồn tại trên thiết bị của bạn", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.mnuAlarm:
                Intent intent3 = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                startActivity(intent3);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        int id = b.getInt("Id");
        String name = b.getString("Name");
        String phone = b.getString("Phone");
        Contact newcontact = new Contact(id,"Image", name, phone);
        if(requestCode==100 && resultCode==150){
            ContactList.add(newcontact);
        }
        else if(requestCode==200 && resultCode==150){
            //trường hợp sửa
            ContactList.set(SelectedItemId,newcontact);
        }
        ListAdapter.notifyDataSetChanged();
        lstContact.setAdapter(ListAdapter);
    }
}