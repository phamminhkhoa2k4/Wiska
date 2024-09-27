/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Cart;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS
 */
@Local
public interface CartSBLocal {

    void addItem(Cart item);

    void removeItem(Integer productId);

    void clearCart();

    List<Cart> getItems();
    
    void updateQuantity(Integer id,int quantiy);
    
    Cart findById(Integer id);
    
}
