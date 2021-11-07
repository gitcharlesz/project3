package com.example.project3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_LIST_ITEM="create table ListItem("
            +"l_id integer primary key autoincrement,"
            +"content text,status integer,time text)";

    public MyOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIST_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        return;
    }
}
