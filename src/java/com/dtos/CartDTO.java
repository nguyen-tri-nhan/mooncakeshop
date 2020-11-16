/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class CartDTO {
    private Map<String, CakeDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<String, CakeDTO> cart) {
        this.cart = cart;
    }

    public Map<String, CakeDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, CakeDTO> cart) {
        this.cart = cart;
    }
    public void add(CakeDTO dto, int newQuantity){
        if(cart == null){
            cart = new HashMap<>();
        }
        if (this.cart.containsKey(String.valueOf(dto.getId()))) {
            int quantity = this.cart.get(String.valueOf(dto.getId())).getQuantity();
            dto.setQuantity(quantity+newQuantity);
        }
        cart.put(String.valueOf(dto.getId()), dto);
    }

    
    public void delete(String id){
        if(this.cart == null){
            return;
        }
        if (this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
    public void update(String id, CakeDTO dto){
        if (this.cart != null){
            if (this.cart.containsKey(id)){
                this.cart.replace(id, dto);
            }
        }
    }
}
