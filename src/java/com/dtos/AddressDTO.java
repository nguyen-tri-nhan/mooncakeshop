/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtos;

/**
 *
 * @author Lenovo
 */
public class AddressDTO {

    /**
     * id varchar(50) NOT NULL PRIMARY KEY, username varchar(50) FOREIGN KEY
     * REFERENCES tblUsers(username), phone varchar(12), fullname varchar(36),
     * addr varchar(50),
     */
    String id, username, fullname, phone, address;

    public AddressDTO() {
    }

    public AddressDTO(String id, String username, String fullname, String phone, String address) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
