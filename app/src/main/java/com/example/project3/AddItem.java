package com.example.project3;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class AddItem extends AppCompatActivity {

    private TextView textcontent;
    private Button addbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);
        init();
    }

    void init()
    {
        textcontent = findViewById(R.id.editTextTextMultiLine);
        addbutton = findViewById(R.id.button);

        addbutton.setOnClickListener(view -> {
            String title;
            title = String.valueOf(textcontent.getText());
            newItem(title);
            finish();
        });
    }

    public static ListItemBean newItem(String title)
    {
        ListItemBean ti = new ListItemBean(title, ListItemBean.UNFINISH, getNowTime());
        ListItemDB.insertListItem(ti);

        int position = MainActivity.la.getItemCount();
        MainActivity.todoitemList.add(position, ti);
        MainActivity.la.notifyItemInserted(position);
        return ti;
    }

    public static String getNowTime(){
        long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d=new Date(time);
        String t=format.format(d);
        return t;
    }
}
