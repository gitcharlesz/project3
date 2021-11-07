package com.example.project3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private FloatingActionButton additem;

    public static List<ListItemBean> todoitemList;

    private LinearLayoutManager lom;
    public static ListAdapter la;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
        additem = findViewById(R.id.floatingActionButton);

        initrecycleview();

        la.setOnFinishListener((view, position) -> markStatus(position, false));
        la.setDeleteListener((view, position) -> {
            deleteItem(position);
        });
    }

    private void initrecycleview()
    {
        todoitemList = ListItemDB.queryAllItems();
        lom = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lom);
        la = new ListAdapter(todoitemList);
        recyclerView.setAdapter(la);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void markStatus(int pos, boolean initial)
    {
        int status;
        if (initial)
        {
            status = ListItemBean.UNFINISH;
        }
        else {
            status = todoitemList.get(pos).getStatus();
            if (status == ListItemBean.FINISH) {
                status = ListItemBean.UNFINISH;
            } else {
                status = ListItemBean.FINISH;
            }
        }
        todoitemList.get(pos).setStatus(status);
        ListItemDB.updateItem(todoitemList.get(pos).getId(),status);
        la.notifyItemChanged(pos);
    }

    private void deleteItem(int pos)
    {
        long lid = todoitemList.get(pos).getId();
        todoitemList.remove(pos);
        ListItemDB.deleteItem(lid);
        la.notifyItemRemoved(pos);
    }

    private void initListener(){
        additem.setOnClickListener(v -> setActivityAddItem());
    }

    private void setActivityAddItem()
    {
        Intent intent = new Intent(MainActivity.this, AddItem.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}