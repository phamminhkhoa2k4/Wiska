
package com.shoes.entity;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */

public class Cart implements Serializable {

    private Integer productId;
    private String title;
    private String thumbnail;
    private int quantity;
    private BigDecimal price;

    public Cart() {
      
    }

    public Cart(Integer productId, String title, int quantity, BigDecimal price, String thumbnail) {
        this.productId = productId;
        this.thumbnail = thumbnail;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer  productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
