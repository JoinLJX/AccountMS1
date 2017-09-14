package com.example.ios28.accountms;

import java.util.Calendar;



import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ios28.accountms.AccountDAO.OutaccountDAO;
import com.example.ios28.accountms.model.Tb_outaccount;


public class OutaccountInfomanage extends Activity {
    protected static final int DATE_DIALOG_ID = 0;
    TextView tvtitle, textView;
    EditText txtMoney, txtTime, txtMark,txtaddress;
    Spinner spType;
    Button btnEdit, btnDel;
    String[] strInfos;
    String strid, strType;

    private int mYear;
    private int mMonth;
    private int mDay;

    OutaccountDAO outaccountDAO = new OutaccountDAO(OutaccountInfomanage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outaccountinfomanage);



        txtMoney = (EditText) findViewById(R.id.txtOutMoney);
        txtTime = (EditText) findViewById(R.id.txtOutTime);
        spType = (Spinner) findViewById(R.id.spOutType);
        txtaddress=(EditText)findViewById(R.id.txtOutHandler);
        txtMark = (EditText) findViewById(R.id.txtOutMark);
        btnEdit = (Button) findViewById(R.id.btnOutUpdate);
        btnDel = (Button) findViewById(R.id.btnOutDel);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strInfos = bundle.getStringArray(Outaccountinfo.FLAG);
        strid = strInfos[0];
        strType = strInfos[1];
        if (strType.equals("btnoutinfo"))
        {
            Tb_outaccount tb_outaccount=outaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));
            txtTime.setText(tb_outaccount.getTime());
            spType.setPrompt(tb_outaccount.getType());
            txtaddress.setText(tb_outaccount.getAddress());
            txtMark.setText(tb_outaccount.getMark());
        }

        txtTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);
            }
        });
        btnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (strType.equals("btnoutinfo"))
                {
                    Tb_outaccount tb_outaccount = new Tb_outaccount();
                    tb_outaccount.setid(Integer.parseInt(strid));
                    tb_outaccount.setMoney(Double.parseDouble(txtMoney
                            .getText().toString()));
                    tb_outaccount.setTime(txtTime.getText().toString());
                    tb_outaccount.setType(spType.getSelectedItem().toString());
                    tb_outaccount.setAddress(txtaddress.getText().toString());
                    tb_outaccount.setMark(txtMark.getText().toString());
                    outaccountDAO.update(tb_outaccount);
                }

                Toast.makeText(OutaccountInfomanage.this, "修改成功!", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });

        btnDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (strType.equals("btnoutinfo"))
                {
                    outaccountDAO.detele(Integer.parseInt(strid));
                }
                Toast.makeText(OutaccountInfomanage.this, "删除成功", Toast.LENGTH_SHORT)
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
