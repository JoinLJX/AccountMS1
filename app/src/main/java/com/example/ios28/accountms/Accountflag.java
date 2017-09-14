package com.example.ios28.accountms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ios28.accountms.AccountDAO.FlagDAO;


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

        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFlag = txtFlag.getText().toString();
                if(!strFlag.isEmpty())
                {
                    FlagDAO flagDAO = new FlagDAO(Accountflag.this);
                }
            }
        });

    }

}
