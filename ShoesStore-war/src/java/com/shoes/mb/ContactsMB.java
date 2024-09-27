/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.shoes.mb;

import com.shoes.entity.Contacts;
import com.shoes.message.Message;
import com.shoes.sb.ContactsFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ASUS
 */
@Named(value = "contactsMB")
@RequestScoped
public class ContactsMB implements Serializable {

    @EJB
    private ContactsFacadeLocal contactsFacade;

    private Message mess;
    private Contacts contact;

    public ContactsMB() {
        contact = new Contacts();
    }

    public String SendContact() {
        if (contact != null) {
            if (contact.getName().equals("")) {
                mess = new Message("Please Enter  Name!!!", "2", true);
            } else if (contact.getEmail().equals("")) {
                mess = new Message("Please Enter Email !!!", "2", true);
            } else if (contact.getSubject().equals("")) {
                mess = new Message("Please Enter Sublect !!!", "2", true);
            } else if (contact.getMessage().equals("")) {
                mess = new Message("Please Enter Message", "2", true);
            } else {
                try {
                    contactsFacade.create(contact);
                    mess = new Message("Sended Sucessfully !!!", "1", true);
                    contact = new Contacts();
                } catch (Exception e) {
                    mess = new Message("Error Server", "3", true);
                }
            }

        } else {
           mess = new Message("Please fill in fields !!!", "2", true); 
        }
        
        return "contact";
    }

    public List<Contacts> List() {
        try {
            List<Contacts> data = contactsFacade.findAll();
            return data;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public void DeleteContact(int id) {

                try {

                    contact = contactsFacade.find(id);
                    contactsFacade.remove(contact);
                    mess = new Message("Deleted Successfully !!!", "1", true);
                } catch (Exception e) {
                     mess = new Message("Error Server", "3", true);
                }

    }

    public Contacts getContact() {
        return contact;
    }

    public void setContact(Contacts contact) {
        this.contact = contact;
    }

    public Message getMess() {
        return mess;
    }

    public void setMess(Message mess) {
        this.mess = mess;
    }

}
