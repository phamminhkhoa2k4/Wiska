/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.shoes.mb;

import com.shoes.entity.Cart;
import com.shoes.entity.Products;
import com.shoes.sb.CartSBLocal;
import com.shoes.sb.ProductsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author ASUS
 */
@Named(value = "cartMB")
@SessionScoped
public class CartMB implements Serializable {

    @EJB
    private CartSBLocal cartSB;

    @EJB
    private ProductsFacadeLocal product;
    
    private Cart productCart;
    
    private int quantity = 1;

    public CartMB() {
        productCart = new Cart();
    }
    
    
//    public String addItem(Integer id,int quantity) {
//       Products data = product.find(id);
//       productCart.setProductId(data.getProductId());
//       productCart.setPrice(data.getPrice());
//       productCart.setThumbnail(data.getThumnail());
//       productCart.setTitle(data.getTitle());
//       productCart.setQuantity(quantity);
//       cartSB.addItem(productCart);
//       clearForm();
//       return null;
//    }
//    

    
    public String addItem(Integer id, int quantity,int userId) {
        if(userId != 0){
            
        Products data = product.find(id);
        

        productCart = new Cart();

   
        productCart.setProductId(data.getProductId());
        productCart.setPrice(data.getPrice());
        productCart.setThumbnail(data.getThumnail());
        productCart.setTitle(data.getTitle());
        
        productCart.setQuantity(quantity);
        
    
        cartSB.addItem(productCart);

      
        clearForm();
        this.setQuantity(1);
        return null;
        }else{
            return "login";
        }
        
    }

    public void decrementQuantity(Integer id){
        Cart cartItem = cartSB.findById(id);
        int quantity = cartItem.getQuantity();
        cartItem.setQuantity(quantity++);
    }
    
    public void incrementQuantity(Integer id){
        Cart cartItem = cartSB.findById(id);
        int quantity = cartItem.getQuantity();
        if(quantity > 0){
            cartItem.setQuantity(quantity--);
        }
    }
    
    public void removeItem(Integer productId) {
        cartSB.removeItem(productId);
    }

    public List<Cart> getCartItems() {
        return cartSB.getItems();
    }

    private void clearForm() {
       productCart = new Cart();
    }
    
    public String ClearCartOrder(){
        cartSB.clearCart();
        return "cart";
    }
    public void clearCart(){
        cartSB.clearCart();
    }
    
    public void updateQuantity(Integer id, int quantity){
        cartSB.updateQuantity(id, quantity);
    }
    public BigDecimal TotalCart(){
        BigDecimal total = BigDecimal.ZERO;   
        for (Cart price : getCartItems() ){
            BigDecimal totalElement = price.getPrice().multiply(BigDecimal.valueOf(price.getQuantity()));
            total = total.add(totalElement);    
        }
        return total;
    }
    
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

}
