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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ios28.accountms.AccountDAO.PwdDAO;

/**
 * 登录模块
 */
public class Login extends AppCompatActivity {

    EditText txtlogin;
    Button btnlogin,btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtlogin = (EditText)findViewById(R.id.txtLogin);
        btnlogin = (Button)findViewById(R.id.btnLogin);
        btnClose = (Button)findViewById(R.id.btnClose);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                PwdDAO pwdDAO = new PwdDAO(Login.this);
                if((pwdDAO.getCount()==0 || pwdDAO.find().getPassword().isEmpty())&&
                        txtlogin.getText().toString().isEmpty()) {
                    startActivity(intent);
                }
                else {
                    if (pwdDAO.find().getPassword().equals(txtlogin.getText().toString())){
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Login.this,"密码错误，请输入正确的密码",Toast.LENGTH_LONG).show();
                    }
                }
                txtlogin.setText("");
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
