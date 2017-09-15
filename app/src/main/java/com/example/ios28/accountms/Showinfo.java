package com.example.ios28.accountms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ios28.accountms.AccountDAO.FlagDAO;
import com.example.ios28.accountms.AccountDAO.InaccountDAO;
import com.example.ios28.accountms.AccountDAO.OutaccountDAO;
import com.example.ios28.accountms.model.Tb_flag;
import com.example.ios28.accountms.model.Tb_inaccount;
import com.example.ios28.accountms.model.Tb_outaccount;

import java.util.List;

public class Showinfo extends AppCompatActivity {
    public static final String FLAG = "id";
    Button btnoutinfo, btnininfo, btnflaginfo;
    ListView lvinfo;
    String strType = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);


        lvinfo = (ListView) findViewById(R.id.lvinfo);
        btnoutinfo = (Button) findViewById(R.id.btnoutinfo);
        btnininfo = (Button) findViewById(R.id.btnininfo);
        btnflaginfo = (Button) findViewById(R.id.btnflginfo);


        ShowInfo(R.id.btnoutinfo);
        btnoutinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ShowInfo(R.id.btnoutinfo);
            }
        });
        btnininfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ShowInfo(R.id.btnininfo);
            }
        });
        btnflaginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ShowInfo(R.id.btnflginfo);
            }
        });
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf('|'));
                Intent intent = null;
                if (strType == "btnoutinfo" ) {
                    intent = new Intent(Showinfo.this, OutaccountInfomanage.class);
                    intent.putExtra(FLAG, new String[] { strid, strType });

                } else if (strType == "btnflaginfo") {
                    intent = new Intent(Showinfo.this, FlagManage.class);
                    intent.putExtra(FLAG, strid);
                }
                else if (strType=="btnininfo"){
                    intent = new Intent(Showinfo.this, InaccountInfomanage.class);
                    intent.putExtra(FLAG, new String[] { strid, strType });
                }
                startActivity(intent);
            }
        });
    }

    private void ShowInfo(int intType){
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        switch (intType) {
            case R.id.btnoutinfo:
                strType = "btnoutinfo";
                OutaccountDAO outaccountinfo = new OutaccountDAO(Showinfo.this);
                List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0,
                        (int) outaccountinfo.getCount());
                strInfos = new String[listoutinfos.size()];
                int i = 0;
                for (Tb_outaccount tb_outaccount : listoutinfos) {

                    strInfos[i] = tb_outaccount.getid() + "|"
                            + tb_outaccount.getType() + " "
                            + String.valueOf(tb_outaccount.getMoney()) + "元"
                            + tb_outaccount.getTime();
                    i++;
                }
                break;
            case R.id.btnininfo:
                strType = "btnininfo";
                InaccountDAO inaccountinfo = new InaccountDAO(Showinfo.this);

                List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0,
                        (int) inaccountinfo.getCount());
                strInfos = new String[listinfos.size()];
                int m = 0;
                for (Tb_inaccount tb_inaccount : listinfos) {

                    strInfos[m] = tb_inaccount.get_id() + "|"
                            + tb_inaccount.getType() + " "
                            + String.valueOf(tb_inaccount.getMoney()) + "元"
                            + tb_inaccount.getTime();
                    m++;
                }
                break;
            case R.id.btnflginfo:
                strType = "btnflaginfo";
                FlagDAO flaginfo = new FlagDAO(Showinfo.this);

                List<Tb_flag> listFlags = flaginfo.getScrollData(0,
                        (int) flaginfo.getCount());
                strInfos = new String[listFlags.size()];
                int n = 0;
                for (Tb_flag tb_flag : listFlags) {

                    strInfos[n] = tb_flag.getid() + "|" + tb_flag.getFlag();
                    if (strInfos[n].length() > 15)
                        strInfos[n] = strInfos[n].substring(0, 15) + "……";
                    n++;
                }
                break;

        }
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        ShowInfo(R.id.btnoutinfo);
    }
}
