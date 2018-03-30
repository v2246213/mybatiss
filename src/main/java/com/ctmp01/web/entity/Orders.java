package com.ctmp01.web.entity;

import com.ctmp01.web.util.RandomUtil;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/3/31 0031.
 */
public class Orders {
    private  int id;
    /**
     * 商家订单编号
     */
    private  String  orderid;
    /**
     * 标题
     */
    private  String title;
    /**
     * 价钱
     */
    private BigDecimal money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Orders(int id, String orderid, String title, BigDecimal money) {
        this.id = id;
        this.orderid = orderid;
        this.title = title;
        this.money = money;
    }
}
