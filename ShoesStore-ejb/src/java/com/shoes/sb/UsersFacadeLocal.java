/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();
    
    Users checkLogin(String username, String password);
    
    public String getAvt(String username, String password);

    public int getId(String username, String password);
    
    public long counts(String role);
    
    public List<Users> ListAdmin(String role);
    
    public List<Users> searchAdminByUsername(String username);
}
