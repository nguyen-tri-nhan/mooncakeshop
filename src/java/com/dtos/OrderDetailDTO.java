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
public class OrderDetailDTO {
    /**
     * id varchar(30) NOT NULL PRIMARY KEY,
	orderID varchar(30) NOT NULL FOREIGN KEY REFERENCES tblOrders(id),
	cakeID varchar(30) NOT NULL FOREIGN KEY REFERENCES tblCakes(id),
	quantity int,
     */
    int id, orderid, cakeid;
    int quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, int orderid, int cakeid, int quantity) {
        this.id = id;
        this.orderid = orderid;
        this.cakeid = cakeid;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getCakeid() {
        return cakeid;
    }

    public void setCakeid(int cakeid) {
        this.cakeid = cakeid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
