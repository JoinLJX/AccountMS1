package com.example.ios28.accountms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 实现创建数据库和数据表的方法
 * Created by ios28 on 17/9/13.
 *
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;//定义数据库版本号
    private static final String DBNAME = "account.db";//定义数据库名

    //定义构造函数
    public DBOpenHelper(Context context)
    {
        super(context,DBNAME,null,VERSION);//重写基类的构造函数
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建支出信息表
        db.execSQL("create table tb_outaccount(_id integer primary key,money decimal,"+
                "time varchar(10),type varchar(10),address varchar(100),mark varchar(200))");

        //创建收入信息表
        db.execSQL("create table tb_inaccount(_id iteger primary key,money decimal,"+
        "time varchar(10),type varchar(10),handler varchar(100),mark varchar(200))");

        //创建密码表
        db.execSQL("create table tb_pwd(password varchar(20))");

        //创建便签表
        db.execSQL("create table tb_flag(_id integer primary key,flag varchar(200))");
    }

    //覆写基类的 onUpgrade 方法，以便数据库版本更新

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
