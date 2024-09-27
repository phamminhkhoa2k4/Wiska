/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Cart;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author ASUS
 */
@Stateful
public class CartSB implements CartSBLocal {

    private List<Cart> cartItems;

    public CartSB() {
        cartItems = new ArrayList<>();
    }

    @Override
    public void addItem(Cart item) {
        for (Cart cartItem : cartItems) {
            if (cartItem.getProductId().equals(item.getProductId())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        cartItems.add(item);
    }

    @Override
    public void removeItem(Integer productId) {
        List<Cart> list = cartItems;
        Iterator<Cart> iterator = list.iterator();
        while (iterator.hasNext()) {
            Cart item = iterator.next();
            if (item.getProductId() == productId) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public List<Cart> getItems() {
        return cartItems;
    }

    @Override
    public void clearCart() {
        cartItems.clear();
    }

    
    
    @Override
    public void updateQuantity(Integer id,int quantity) {
         List<Cart> list  = cartItems;
         Iterator<Cart> iterator = list.iterator();
         while(iterator.hasNext()){
             Cart item = iterator.next();
             if(item.getProductId() == id){
                 int newQuantiy = item.getQuantity() + quantity;
                 item.setQuantity(newQuantiy);
                 break;
             }
                     
         }
    }

    @Override
    public Cart findById(Integer id) {
         Cart cartItem = new Cart();
         List<Cart> list  = cartItems;
         Iterator<Cart> iterator = list.iterator();
         while(iterator.hasNext()){
             Cart item = iterator.next();
             if(item.getProductId() == id){
                 
                 cartItem = item;
             }
                     
         }
         
         return cartItem;
    }
}
