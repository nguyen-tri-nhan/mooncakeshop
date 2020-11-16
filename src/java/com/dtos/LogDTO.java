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
public class LogDTO {
    int id;
    String username, act, description;
    Timestamp actionDate;

    public LogDTO() {
    }

    public LogDTO(int id, String username, String act, String description, Timestamp actionDate) {
        this.id = id;
        this.username = username;
        this.act = act;
        this.description = description;
        this.actionDate = actionDate;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }
    
    
}
