/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Products;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS
 */
@Local
public interface ProductsFacadeLocal {

    void create(Products products);

    void edit(Products products);

    void remove(Products products);

    Products find(Object id);

    List<Products> findAll();

    List<Products> findRange(int[] range);

    int count();
    
    List<Products> getProductsByComming(String value);
    
    List<Products> findAll(int pageNumber, int pageSize);
    
    long counts();
    
    public List<Products> searchProductsByTitle(String title);
    
    public List<Products> listProductReceipt(int id);
}
