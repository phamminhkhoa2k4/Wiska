/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Blogs;
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
public class BlogsFacade extends AbstractFacade<Blogs> implements BlogsFacadeLocal {

    @PersistenceContext(unitName = "ShoesStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlogsFacade() {
        super(Blogs.class);
    }

    @Override
    public List<Blogs> searchBlogsByTitle(String BlogTitle) {
        Query query = em.createQuery(
            "SELECT p FROM Blogs p WHERE p.title LIKE :title");
        query.setParameter("title", "%" + BlogTitle + "%");
        return query.getResultList();
    }
    
}
