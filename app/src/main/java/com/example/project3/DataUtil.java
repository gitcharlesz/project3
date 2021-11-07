package com.example.project3;
import android.content.ContentValues;

public class DataUtil {
    public static ContentValues getListItemCV(ListItemBean listItem){
        ContentValues values=new ContentValues();
        values.put("content",listItem.getContent());
        values.put("status",listItem.getStatus());
        values.put("time",listItem.getTime());
        return values;
    }
    public static ContentValues generateCV(String content,int status){
        ContentValues values=new ContentValues();
        values.put("content",content);
        values.put("status",status);
        return values;
    }
    public static ContentValues generateCV(int status){
        ContentValues values=new ContentValues();
        values.put("status",status);
        return values;
    }
}
