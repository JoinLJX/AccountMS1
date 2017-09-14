package com.example.ios28.accountms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {


    GridView gvInfo;
    String[] titles = new String[]{"新增支出","新增收入","我的支出","我的收入","数据管理","系统设置",
            "收支便签","退出"};

    int [] images = new int[]{R.drawable.addoutaccount,R.drawable.addinaccount,R.drawable.outaccountinfo,
            R.drawable.inaccountinfo,R.drawable.showinfo,R.drawable.sysset,R.drawable.accountflag,R.drawable.exit};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvInfo=(GridView)findViewById(R.id.gvInfo);
        pictureAdapter adapter = new pictureAdapter(titles,images,this);
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = null;
                switch (arg2)
                {
                    case 0:
                        intent=new Intent(MainActivity.this,AddOutaccount.class);//打开新增支出
                        startActivity(intent);
                        break;

                    case 1:
                        intent=new Intent(MainActivity.this,AddInaccount.class);//打开新增收入
                        startActivity(intent);
                        break;

                    case 2:
                        intent=new Intent(MainActivity.this,Outaccountinfo.class);//打开我的支出
                        startActivity(intent);
                        break;

                    case 3:
                        intent=new Intent(MainActivity.this,Inaccountinfo.class);//打开我的收入
                        startActivity(intent);
                        break;

                    case 4:
                        intent=new Intent(MainActivity.this,Showinfo.class);//打开数据管理
                        startActivity(intent);
                        break;

                    case 5:
                        intent=new Intent(MainActivity.this,Sysset.class);//打开系统设置
                        startActivity(intent);
                        break;

                    case 6:
                        intent=new Intent(MainActivity.this,Accountflag.class);//打开收支便签
                        startActivity(intent);
                        break;

                    case 7:
                        finish();//关闭当前Activty
                }

            }
        });



    }
}
