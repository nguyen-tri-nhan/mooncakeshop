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
public class TrackDTO {
    int id;
    String fullname, phone, address, type;
    Timestamp orderdate;

    public TrackDTO() {
    }

    public TrackDTO(int id, String fullname, String phone, String address, String type, Timestamp orderdate) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.orderdate = orderdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Timestamp orderdate) {
        this.orderdate = orderdate;
    }

    
    
}
