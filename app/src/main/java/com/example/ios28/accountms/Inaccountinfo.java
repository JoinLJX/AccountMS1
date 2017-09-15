package com.example.ios28.accountms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ios28.accountms.AccountDAO.InaccountDAO;
import com.example.ios28.accountms.model.Tb_inaccount;

import java.util.List;

public class Inaccountinfo extends AppCompatActivity {

    public static final String FLAG="id";//常量，作为请求码
    ListView Ivinfo;//listview对象
    String strType="";//创建字符串，记录管理类型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccountinfo);

        Ivinfo=(ListView)findViewById(R.id.lvinaccountinfo);
        ShowInfo(R.id.btnininfo);

        Ivinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf('|'));
                Intent intent = new Intent(Inaccountinfo.this, InaccountInfomanage.class);
                intent.putExtra(FLAG, new String[] { strid, strType });
                startActivity(intent);
            }
        });
    }
    private void  ShowInfo(int intType){
        String[] strInfos=null;
        ArrayAdapter<String> arrayAdapter=null;
        strType="btnininfo";

        InaccountDAO inaccountinfo=new InaccountDAO(Inaccountinfo.this);
        //获取所有收入信息，并存到list泛型集合中
        List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0,
                (int) inaccountinfo.getCount());
        strInfos = new String[listinfos.size()];
        int m = 0;
        for (Tb_inaccount tb_inaccount : listinfos) {
            strInfos[m] = tb_inaccount.get_id() + "|" + tb_inaccount.getType()
                    + " " + String.valueOf(tb_inaccount.getMoney()) + "Ԫ     "
                    + tb_inaccount.getTime();
            m++;//
        }
        //初始化ArrayAdapter
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfos);
        Ivinfo.setAdapter(arrayAdapter);
    }
    protected void onRestart() {

        super.onRestart();
        ShowInfo(R.id.btnininfo);
    }

}
