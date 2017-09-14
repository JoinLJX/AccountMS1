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

import com.example.ios28.accountms.AccountDAO.PwdDAO;
import com.example.ios28.accountms.model.Tb_pwd;

/**
 * 系统设置功能模块
 */
public class Sysset extends AppCompatActivity {

    private EditText txtpwd;
    private Button btnSet,btnsetCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sysset);
        txtpwd = (EditText)findViewById(R.id.txtPwd);//获取输入密码的文本框
        btnSet = (Button)findViewById(R.id.btnSet);//获取设置按钮
        btnsetCancel = (Button)findViewById(R.id.btnsetCancel);//获取取消按钮

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PwdDAO pwdDAO = new PwdDAO(Sysset.this);
                Tb_pwd tb_pwd = new Tb_pwd(txtpwd.getText().toString());
                if(pwdDAO.getCount()==0){
                    pwdDAO.add(tb_pwd);
                }
                else {
                    pwdDAO.update(tb_pwd);
                }
                Toast.makeText(Sysset.this,"密码设置成功！",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        btnsetCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtpwd.setText("");
                txtpwd.setHint("请输入密码");
            }
        });
    }

}
