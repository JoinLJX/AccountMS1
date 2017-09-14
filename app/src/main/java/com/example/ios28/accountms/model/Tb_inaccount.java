package com.example.ios28.accountms.model;

/**
 * Created by ios28 on 17/9/13.
 */

public class Tb_inaccount {
    private int _id;//存储收入编号
    private double money;//收入金额
    private String time;//收入时间
    private String type;//收入类别
    private String handler;//收入付款方
    private String mark;//收入备注

    //定义无参构造方法
    public Tb_inaccount() {
        super();
    }

    //定义有参构造方法，用来初始化收入信息实体类中的各个字段
    public Tb_inaccount(int _id, double money, String time, String type, String handler, String
            mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.handler = handler;
        this.mark = mark;
    }

    public int get_id() {
        return _id;
    }

    public double getMoney() {
        return money;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getHandler() {
        return handler;
    }

    public String getMark() {
        return mark;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
