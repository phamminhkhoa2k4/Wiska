package com.shoes.mb;

import com.shoes.entity.Cart;
import com.shoes.entity.OrderDetail;
import com.shoes.entity.Orders;
import com.shoes.entity.Products;
import com.shoes.message.Message;
import com.shoes.sb.CartSBLocal;
import com.shoes.sb.OrderDetailFacadeLocal;
import com.shoes.sb.OrdersFacadeLocal;
import com.shoes.sb.ProductsFacadeLocal;
import com.shoes.sb.UsersFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ASUS
 */
@Named(value = "odersMB")
@RequestScoped
public class OdersMB implements Serializable {

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private CartSBLocal cartSB;

    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;

    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    private Orders order;
    private List<Products> products;
    private List<OrderDetail> detail;

    private String lastname;
    private String firstname;

    private Message mess;

    private OrderDetail orderDetail;

    public OdersMB() {
        order = new Orders();

    }

    public String checkout(int userid, List<Cart> carts) {

        order.setUserId(usersFacade.find(userid));
        Date time = new Date();
        order.setOrderDate(time);

        try {
            order.setName(lastname + " " + firstname);
            order.setPaid(false);
            ordersFacade.create(order);

            for (Cart cart : carts) {
                OrderDetail newOrderDetail = new OrderDetail();
                newOrderDetail.setOrderId(order);
                try {
                    newOrderDetail.setProductId(productsFacade.find(cart.getProductId()));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                newOrderDetail.setQuantity(cart.getQuantity());
                BigDecimal quantity = BigDecimal.valueOf(cart.getQuantity());
                BigDecimal total = cart.getPrice().multiply(quantity);
                newOrderDetail.setTotal(total);
                try {
                    orderDetailFacade.create(newOrderDetail);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

        } catch (Exception ex) {

        }
        return "confirmation";
    }

    public String ConfirmPayment(int id) {

        Orders or = ordersFacade.find(id);
        or.setPaid(true);
        ordersFacade.edit(or);
        mess = new Message("Comfirm Successfully !!!", "1", true);
        return null;
    }

    public List<Orders> findAll() {
        List<Orders> orders = ordersFacade.findAll();
        return orders;
    }

    public BigDecimal revenue() {
        BigDecimal revenue = BigDecimal.ZERO;
        List<OrderDetail> orders = orderDetailFacade.findAll();
        for (OrderDetail order : orders) {
            revenue = revenue.add(order.getTotal());
        }
        return revenue;
    }

   
    public String findo(Integer id) {
        
            
          
            
            detail = orderDetailFacade.getListOrderById(id);
            order = ordersFacade.find(id);
            return "order-detail"; 
    }

    public String deleteo(Integer id) {
        try {
            Orders order = ordersFacade.find(id);
            ordersFacade.remove(order);
            mess = new Message("Deleted Successfully !!!", "1", true);
            return null;
        } catch (Exception ex) {
            mess = new Message("Error Server !!!", "3", true);
            return null;
        }
    }

    public BigDecimal TotalOrder(Integer id) {
        BigDecimal total = BigDecimal.ZERO;
        int parserId = Integer.parseInt(id.toString());
        List<OrderDetail> orderDetails = orderDetailFacade.getListOrderById(parserId);
        for (OrderDetail orderDetail : orderDetails) {
            Products product = productsFacade.find(orderDetail.getProductId().getProductId());
            BigDecimal quantity = new BigDecimal(orderDetail.getQuantity()); 
            total = total.add(quantity.multiply(product.getPrice()));
        }
        return total;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public List<OrderDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<OrderDetail> detail) {
        this.detail = detail;
    }

    public Message getMess() {
        return mess;
    }

    public void setMess(Message mess) {
        this.mess = mess;
    }

}
