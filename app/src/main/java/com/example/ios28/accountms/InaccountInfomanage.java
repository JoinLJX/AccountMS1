package com.example.ios28.accountms;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ios28.accountms.AccountDAO.InaccountDAO;
import com.example.ios28.accountms.model.Tb_inaccount;

import java.util.Calendar;

public class InaccountInfomanage extends Activity {

    protected static final int DATE_DIALOG_ID = 0;
    EditText txtMoney, txtTime, txtHA, txtMark;
    Spinner spType;
    Button btnEdit, btnDel;
    String[] strInfos;
    String strid, strType;

    private int mYear;
    private int mMonth;
    private int mDay;


    InaccountDAO inaccountDAO = new InaccountDAO(InaccountInfomanage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccountinfomanage);

        txtMoney = (EditText) findViewById(R.id.txtInMoney);
        txtTime = (EditText) findViewById(R.id.txtInTime);
        spType = (Spinner) findViewById(R.id.spInType);
        txtMark = (EditText) findViewById(R.id.txtInMark);
        txtHA=(EditText)findViewById(R.id.txtInHandler);
        btnEdit = (Button) findViewById(R.id.btnInEdit);
        btnDel = (Button) findViewById(R.id.btnInDelete);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strInfos = bundle.getStringArray(Inaccountinfo.FLAG);
        strid = strInfos[0];
        strType = strInfos[1];

       if (strType.equals("btnininfo"))
        {
            Tb_inaccount tb_inaccount =inaccountDAO.find(Integer
                    .parseInt(strid));
            txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));
            txtTime.setText(tb_inaccount.getTime());
            spType.setPrompt(tb_inaccount.getType());
            txtHA.setText(tb_inaccount.getHandler());
            txtMark.setText(tb_inaccount.getMark());
        }

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
              if (strType.equals("btnininfo"))
                {
                    Tb_inaccount tb_inaccount = new Tb_inaccount();
                    tb_inaccount.set_id(Integer.parseInt(strid));
                    tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText()
                            .toString()));
                    tb_inaccount.setTime(txtTime.getText().toString());
                    tb_inaccount.setType(spType.getSelectedItem().toString());
                    tb_inaccount.setHandler(txtHA.getText().toString());
                    tb_inaccount.setMark(txtMark.getText().toString());
                    inaccountDAO.update(tb_inaccount);
                }
                Toast.makeText(InaccountInfomanage.this, "修改成功!", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                 if (strType.equals("btnininfo"))
                {
                    inaccountDAO.delete(Integer.parseInt(strid));
                }
                Toast.makeText(InaccountInfomanage.this, "删除成功", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void updateDisplay() {
        txtTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));
    }


}
