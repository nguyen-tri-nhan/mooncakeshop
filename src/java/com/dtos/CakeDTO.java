/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtos;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class CakeDTO {
//    tblCakes(id,name,descripton,categoryid,createdate,expirationdate,img,price,quantity,isVisible)

    String name, descripton, img;
    Date createDate, expirationDate;
    double price;
    int id, categoryID, quantity;
    boolean visible;

    public CakeDTO() {
    }

    public CakeDTO(int id, String name, String descripton, String img, Date createDate, Date expirationDate, double price, int categoryID, int quantity, boolean visible) {
        this.id = id;
        this.name = name;
        this.descripton = descripton;
        this.img = img;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
