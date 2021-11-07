package com.example.project3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListItemDB {
    private static final MyOpenHelper dbHelper=new MyOpenHelper(MyApplication.getContext(),"todolistdb.db",null,1);

    public static long insertListItem(ListItemBean listItem){
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        long id=database.insert("ListItem",null, DataUtil.getListItemCV(listItem));
        return id;
    }
    public static List<ListItemBean> queryAllItems(){
        List<ListItemBean> list=new ArrayList<>();
        ListItemBean listItem;
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("ListItem",null,null,null,null,null,"l_id asc");
        while(cursor.moveToNext()){
            @SuppressLint("Range") long id=cursor.getLong(cursor.getColumnIndex("l_id"));
            @SuppressLint("Range") String content=cursor.getString(cursor.getColumnIndex("content"));
            @SuppressLint("Range") int status=cursor.getInt(cursor.getColumnIndex("status"));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
            listItem=new ListItemBean(content,status,time);
            listItem.setId(id);
            list.add(listItem);
        }
        cursor.close();
        return list;
    }
    public static void updateItem(long id,int status){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=DataUtil.generateCV(status);
        db.update("ListItem",values,"l_id=?",new String[]{id+""});
    }
    public static void deleteItem(long id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("ListItem","l_id=?",new String[]{id+""});
    }
}
