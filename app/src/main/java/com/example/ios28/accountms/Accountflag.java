package com.example.ios28.accountms;

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


public class Accountflag extends AppCompatActivity {

    EditText txtFlag;
    Button btnflagSaveButton;
    Button btnflagCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountflag);

        txtFlag = (EditText)this.findViewById(R.id.txtFlag);
        btnflagSaveButton = (Button)findViewById(R.id.btnflagSave);
        btnflagCancelButton = (Button)findViewById(R.id.btnflagCancel);

        //保存便签按钮事件
        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFlag = txtFlag.getText().toString();
                if(!strFlag.isEmpty())
                {
                    FlagDAO flagDAO = new FlagDAO(Accountflag.this);
                    Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxId()+1,strFlag);
                    flagDAO.add(tb_flag);
                    //弹出信息提示
                    Toast.makeText(Accountflag.this,"【新增便签】数据添加成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Accountflag.this,"请输入便签！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //清空便签文本框事件
        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFlag.setText("");//清空文本框
            }
        });

    }

}
