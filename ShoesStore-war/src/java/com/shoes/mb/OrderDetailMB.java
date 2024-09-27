/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.shoes.mb;

import com.shoes.entity.Cart;
import com.shoes.entity.OrderDetail;
import com.shoes.sb.CartSBLocal;
import com.shoes.sb.OrderDetailFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ASUS
 */
@Named(value = "orderDetailMB")
@RequestScoped
public class OrderDetailMB implements Serializable {

    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;

    @EJB
    private CartSBLocal cartSB;

    
    /**
     * Creates a new instance of OrderDetailMB
     */
    public OrderDetailMB() {
    }
    
//    public String addOrderDetail(OrderDetail orderDetail){
//        
//        List<Cart> carts = cartSB.getItems();
//        
//        for(Cart cart: carts){
//            orderDetail.setOrderId(orderId);
//            try{
//                orderDetailFacade.create(orderDetail);
//            }catch(Exception ex){
//                
//            }
//        }
//        
//        return "cart";
//    }
}
