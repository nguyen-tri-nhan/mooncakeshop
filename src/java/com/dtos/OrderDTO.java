/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtos;

import java.sql.Timestamp;

/**
 *
 * @author Lenovo
 */
public class OrderDTO {

    /**
     * id varchar(30) NOT NULL PRIMARY KEY, username varchar(50) FOREIGN KEY
     * REFERENCES tblUsers(username), addressid varchar(50) FOREIGN KEY
     * REFERENCES tblAddresses(id), orderdate datetime, cashtype int NOT NULL
     * FOREIGN KEY REFERENCES tblCashTypes(id), iscashed bit, isdelivered bit,
     */
    int id, addressid;
    String username;
    Timestamp orderdate;
    boolean cashed, delivered;
    int cashtype;

    public OrderDTO() {
    }

    public OrderDTO(int id, String username, int addressid, Timestamp orderdate, boolean cashed, boolean delivered, int cashtype) {
        this.id = id;
        this.addressid = addressid;
        this.username = username;
        this.orderdate = orderdate;
        this.cashed = cashed;
        this.delivered = delivered;
        this.cashtype = cashtype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Timestamp orderdate) {
        this.orderdate = orderdate;
    }

    public boolean isCashed() {
        return cashed;
    }

    public void setCashed(boolean cashed) {
        this.cashed = cashed;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public int getCashtype() {
        return cashtype;
    }

    public void setCashtype(int cashtype) {
        this.cashtype = cashtype;
    }

}
