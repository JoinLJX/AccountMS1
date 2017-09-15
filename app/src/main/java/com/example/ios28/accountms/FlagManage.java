package com.example.ios28.accountms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ios28.accountms.AccountDAO.FlagDAO;
import com.example.ios28.accountms.model.Tb_flag;

public class FlagManage extends AppCompatActivity {

    EditText txtFlag;
    Button btnEdit,btnDel;
    String strid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flagmanage);

        txtFlag = (EditText)findViewById(R.id.txtFlagManage);
        btnEdit = (Button)findViewById(R.id.btnFlagManageEdit);
        btnDel = (Button)findViewById(R.id.btnFlagManageDelete);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strid = bundle.getString(Showinfo.FLAG);
        final FlagDAO flagDAO = new FlagDAO(FlagManage.this);
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tb_flag tb_flag = new Tb_flag();
                tb_flag.setid(Integer.parseInt(strid));
                tb_flag.setFlag(txtFlag.getText().toString());
                flagDAO.update(tb_flag);
                Toast.makeText(FlagManage.this,"【便签数据】修改成功!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagDAO.detele(Integer.parseInt(strid));
                Toast.makeText(FlagManage.this,"【便签数据】删除成功!",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
