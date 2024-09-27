/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ASUS
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal {

    @PersistenceContext(unitName = "ShoesStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }

    @Override
    public List<Products> getProductsByComming(String value) {
        Query query = em.createQuery("SELECT p FROM Products p WHERE p.availability = :availability");
        query.setParameter("availability", value);
        return query.getResultList();
    }
    

    @Override
   public List<Products> findAll(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT p FROM Products p");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long counts() {
        Query query = em.createQuery("SELECT COUNT(p) FROM Products p");
        return (long) query.getSingleResult();
    }

    @Override
    public List<Products> searchProductsByTitle(String title) {
        Query query = em.createQuery(
            "SELECT p FROM Products p WHERE p.title LIKE :title");
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    @Override
    public List<Products> listProductReceipt(int id) {
        Query query = em.createQuery("SELECT p FROM Products p WHERE p.productId = :productId");
        query.setParameter("productId", id);
        return query.getResultList();
    }
}
