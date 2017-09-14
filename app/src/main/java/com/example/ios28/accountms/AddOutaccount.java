package com.example.ios28.accountms;

import java.util.Calendar;



import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ios28.accountms.AccountDAO.OutaccountDAO;
import com.example.ios28.accountms.model.Tb_outaccount;

public class AddOutaccount extends Activity {

    protected static final int DATE_DIALOG_ID = 0;  //创建日期对话框常量
    EditText txtOutMoney, txtOutTime, txtOutHandler, txtOutMark;  //创建4个EditText对象 
    Spinner spOutType;   //创建Spinner对象 
    Button btnOutSaveButton; //创建BUTTON对象"保存" 
    Button btnOutCancelButton;//创建BUTTON对象"取消"
    private int mYear; //年
    private int mMonth; //月
    private int mDay; //日



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addoutaccount);
        init();

        txtOutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        btnOutSaveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String strMoney = txtOutMoney.getText().toString();
                if (!strMoney.isEmpty()) {

                    OutaccountDAO outaccountDAO = new OutaccountDAO(
                            AddOutaccount.this);

                    Tb_outaccount tb_outaccount = new Tb_outaccount(
                            outaccountDAO.getMaxId() + 1,
                            Double.parseDouble(strMoney),
                            txtOutTime.getText().toString(),
                            spOutType.getSelectedItem().toString(),
                            txtOutHandler.getText().toString(),
                            txtOutMark.getText().toString());
                    outaccountDAO.add(tb_outaccount);

                    Toast.makeText(AddOutaccount.this, "保存成功",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddOutaccount.this, "保存失败",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnOutCancelButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                txtOutMoney.setText("");
                txtOutMoney.setHint("0.00");
                txtOutTime.setText("");
                txtOutTime.setHint("2011-01-01");
                txtOutHandler.setText("");
                txtOutMark.setText("");
                spOutType.setSelection(0);
                finish();
            }
        });



    }


    public void init() {
        txtOutMoney = (EditText) findViewById(R.id.txtOutMoney);
        txtOutHandler = (EditText) findViewById(R.id.txtOutHandler);
        txtOutTime = (EditText) findViewById(R.id.txtOutTime);
        txtOutMark = (EditText) findViewById(R.id.txtOutMark);
        spOutType = (Spinner) findViewById(R.id.spOutType);
        btnOutSaveButton = (Button) findViewById(R.id.btnOutSave);
        btnOutCancelButton = (Button) findViewById(R.id.btnOutCancel);

    }

    private void updateDisplay() {
        txtOutTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay));
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
                              int dayOfMonth)
        {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };
}
