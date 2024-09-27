/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Contacts;
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
public class ContactsFacade extends AbstractFacade<Contacts> implements ContactsFacadeLocal {

    @PersistenceContext(unitName = "ShoesStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactsFacade() {
        super(Contacts.class);
    }

    @Override
    public List<Contacts> searchContactsByMess(String ContactMessage) {
        Query query = em.createQuery(
            "SELECT p FROM Contacts p WHERE p.message LIKE :message");
        query.setParameter("message", "%" + ContactMessage + "%");
        return query.getResultList();
    }
    
}
