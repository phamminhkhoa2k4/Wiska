/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ASUS
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "ShoesStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    

    @Override
    public String getAvt(String username, String password) {
        String avt = null;
        Query query = em.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            Users user = (Users) query.getSingleResult();
            avt=user.getAvartar();
        } catch (NoResultException ex) {
            // Nếu không có kết quả, flag vẫn giữ nguyên là false
        }
        return avt;
    }

    @Override
    public int getId(String username, String password) {
        int id=0;
        Query query = em.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            Users user = (Users) query.getSingleResult();
            id=user.getUserId();
        } catch (NoResultException ex) {
            // Nếu không có kết quả, flag vẫn giữ nguyên là false
        }
        return id;
    }

    @Override
    public Users checkLogin(String username, String password) {
        Query query = em.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            Users user = (Users) query.getSingleResult();
            return user;
        } catch (NoResultException ex) {
            // Nếu không có kết quả, flag vẫn giữ nguyên là false
            return null;
        }
        
    }


 

  
    @Override
    public long counts(String role) {
         Query query = em.createQuery("SELECT COUNT(p) FROM Users p WHERE P.role = :role");
         query.setParameter("role", role);
        return (long) query.getSingleResult();
    }

    @Override
    public List<Users> ListAdmin(String role) {
       Query query = em.createQuery("SELECT p FROM Users p WHERE p.role = :role");
        query.setParameter("role", role);
        return query.getResultList();
    }

    @Override
    public List<Users> searchAdminByUsername(String username) {
       Query query = em.createQuery("SELECT p FROM Users p WHERE p.role = :role AND p.username LIKE :username");
        query.setParameter("role", "admin");
        query.setParameter("username", "%" + username + "%");
        return query.getResultList();
    }
    
}
