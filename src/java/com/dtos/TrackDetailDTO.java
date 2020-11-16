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
public class TrackDetailDTO {
    String cakename;
    int quantity;

    public TrackDetailDTO() {
    }

    public TrackDetailDTO(String cakename, int quantity) {
        this.cakename = cakename;
        this.quantity = quantity;
    }

    public String getCakename() {
        return cakename;
    }

    public void setCakename(String cakename) {
        this.cakename = cakename;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
