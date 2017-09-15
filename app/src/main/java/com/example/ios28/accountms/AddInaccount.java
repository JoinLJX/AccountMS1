package com.example.ios28.accountms;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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

public class AddInaccount extends Activity {

    protected static final int DATE_DIALOG_ID = 0;
    EditText txtInMoney, txtInTime, txtInHandler, txtInMark;
    Spinner spInType;
    Button btnInSaveButton;
    Button btnInCancelButton;
    private int mYear;
    private int mMonth;
    private int mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinaccount);

        txtInMoney = (EditText) findViewById(R.id.txtInMoney);
        txtInTime = (EditText) findViewById(R.id.txtInTime);
        txtInHandler = (EditText) findViewById(R.id.txtInHandler);
        txtInMark = (EditText) findViewById(R.id.txtInMark);
        spInType = (Spinner) findViewById(R.id.spInType);
        btnInSaveButton = (Button) findViewById(R.id.btnInSave);
        btnInCancelButton = (Button) findViewById(R.id.btnInCancel);

        //时间文本框单击事件
        txtInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);//显示日期选择对话框
            }
        });

        //保存按钮单击事件
        btnInSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String strInMoney = txtInMoney.getText().toString();
                if (!strInMoney.isEmpty()) {

                    InaccountDAO inaccountDAO = new InaccountDAO(
                            AddInaccount.this);

                    Tb_inaccount tb_inaccount = new Tb_inaccount(
                            inaccountDAO.getMaxId() + 1, Double
                            .parseDouble(strInMoney), txtInTime
                            .getText().toString(), spInType
                            .getSelectedItem().toString(),
                            txtInHandler.getText().toString(),
                            txtInMark.getText().toString());
                    inaccountDAO.add(tb_inaccount);

                    Toast.makeText(AddInaccount.this, "保存成功",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddInaccount.this, "请输入金额",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //取消按钮单击事件
        btnInCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInMoney.setText("");
                txtInMoney.setHint("2000.00");
                txtInTime.setText("");
                txtInTime.setHint("2017-9-14 ");
                txtInHandler.setText("");
                txtInMark.setText("");
                spInType.setSelection(0);
                finish();
            }
        });


        final Calendar c = Calendar.getInstance();//获取当前系统日期
        mYear =c.get(Calendar.YEAR);//获取年份
        mMonth=c.get(Calendar.MONTH);//获取月份
        mDay=c.get(Calendar.DAY_OF_MONTH);//获取天数
        updateDisplay();//显示当前系统时间

    }

    private void updateDisplay(){
        txtInTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth+1).append("-").append(mDay));
    }

    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID://弹出时间选择对话框
                return new DatePickerDialog(this,mDateSetListener,mYear,mMonth,mDay);
        }
        return null;
    }

    //显示用户设置的时间
    private DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener(){

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            mYear=year;
            mMonth=monthOfYear;
            mDay=dayOfMonth;
            updateDisplay();
        }
    };

}
