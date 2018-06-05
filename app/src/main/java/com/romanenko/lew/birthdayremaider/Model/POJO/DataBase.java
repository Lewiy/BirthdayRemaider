package com.romanenko.lew.birthdayremaider.Model.POJO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    /**
     * Имя файла базы данных
     */
    private static final String DATABASE_NAME = "hotel.db";

    /**
     * Версия базы данных. При изменении схемы увеличить на единицу
     */

    private static final int DATABASE_VERSION = 1;
    public DataBase (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
