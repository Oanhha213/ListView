package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter{
    //Nguồn dữ liệu cho Adapter
    private ArrayList<Contact> data;
    //Ngữ cảnh của ứng dụng
    private Activity context;
    //Đối tượng để phân tích layout
    private LayoutInflater inflater;
    public Adapter(ArrayList<Contact> data, Activity activity){
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    };
    @Override
    public int getCount() {return data.size();}
    @Override
    public Object getItem(int i) {return data.get(i);}
    @Override
    public long getItemId(int i) {return data.get(i).getId();}
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        View v = view;
        if(v==null)
            v = inflater.inflate(R.layout.layout2, null);
        ImageView imgprofile = v.findViewById(R.id.imageView);
        TextView tvname = v.findViewById(R.id.txtName);
        tvname.setText(data.get(i).getName());
        TextView tvphone = v.findViewById(R.id.txtPhone);
        tvphone.setText(data.get(i).getPhone());
        return v;
    }
}
