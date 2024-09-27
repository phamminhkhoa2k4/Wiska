/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.shoes.mb;

import com.shoes.entity.Users;
import com.shoes.message.Message;
import com.shoes.sb.UsersFacadeLocal;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author ASUS
 */
@Named(value = "usersMB")
@SessionScoped
public class UsersMB implements Serializable {

    @EJB
    private UsersFacadeLocal usersFacade;

    private Users user;
    private Part file;
    private String comfirmPassword;
    
    private Message mess;
    private String username;
    private String password;
    private boolean loggedIn;
    private String loggedInUser;
    private int Iduser;
    private String role;
    private String avtuser;

    public UsersMB() {
        user = new Users();
        loggedIn = false;
        loggedInUser = null;

    }
    
    

   
    
    

    public String Register() throws IOException {
        
        
        if (user == null){
            mess = new Message("Please fill in fields !!!", "2", true);
            return null;
        }else if(user.getUsername().equals("")){
             mess = new Message("Please Enter Username !!!", "2", true);
             return null;

        }else if(user.getEmail().equals("")){
             mess = new Message("Please Enter Email !!!", "2", true);
            return null;

        }else if(user.getPassword().equals("")){
             mess = new Message("Please Enter Password  !!!", "2", true);
            return null;

        } else if(comfirmPassword.equals("")) {
             mess = new Message("Please Enter Comfirm Password !!!", "2", true);
            return null;
        }else{
            if (user.getPassword().equals(comfirmPassword)) {
                user.setRole("user");
                user.setAvartar("default.jpg");
                try {
                     usersFacade.create(user);
                } catch (Exception e) {
                }
               

            } else {
                mess = new Message("Password not match !!!", "3", true);
                return null;
            }
            avtuser = usersFacade.getAvt(user.getUsername(), user.getPassword());
            Iduser = usersFacade.getId(user.getUsername(), user.getPassword());
            loggedIn = true;
            loggedInUser = user.getUsername();

            mess = new Message("Registered Successfully !!!", "1", true);
            return "index";
        }

        
    }

    public String AllocatingAdmin() throws FileNotFoundException, IOException {
        if (user == null){
            mess = new Message("Please fill in fields !!!", "2", true);
            return null;
        }else if(user.getUsername().equals("")){
             mess = new Message("Please Enter Username !!!", "2", true);
             return null;

        }else if(user.getEmail().equals("")){
             mess = new Message("Please Enter Email !!!", "2", true);
            return null;

        }else if(user.getPassword().equals("")){
             mess = new Message("Please Enter Password  !!!", "2", true);
            return null;

        } else if(comfirmPassword.equals("")) {
             mess = new Message("Please Enter Comfirm Password !!!", "2", true);
            return null;
        }else{
            if (user.getPassword().equals(this.getComfirmPassword())) {
                String filename = file.getSubmittedFileName();
                user.setAvartar(file.getSubmittedFileName());
                InputStream input = file.getInputStream();
                FileOutputStream output = new FileOutputStream("F:\\ShoesStore\\ShoesStore-war\\web\\resources\\users\\" + filename);
                byte[] buf = new byte[1024];
                int len;
                while ((len = input.read(buf)) > 0) {
                    output.write(buf, 0, len);
                }
                user.setRole("admin");
                usersFacade.create(user);
                mess = new Message("Registered Successfully !!!", "1", true);
                return "user-list";

            } else {
                mess = new Message("Password not match !!!", "3", true);
                return null;
            }
        }
        
    }

    public String Login() {
        if (username.equals("")){
           mess = new Message("Please Enter Username  !!!", "2", true);
           return null;
        }else if(password.equals("")) {
            mess = new Message("Please Enter Password  !!!", "2", true);
           return null;
        }
        
            
            try {
            Users userData = usersFacade.checkLogin(username, password);
            if (userData.getRole().equals("admin")) {
                avtuser = usersFacade.getAvt(username, password);
                Iduser = usersFacade.getId(username, password);
                loggedIn = true;
                loggedInUser = username;
                role = userData.getRole();
                return "index";
            } else if (userData.getRole().equals("user")) {
                avtuser = usersFacade.getAvt(username, password);
                Iduser = usersFacade.getId(username, password);
                loggedIn = true;
                loggedInUser = username;
                role = userData.getRole();
                return "index";
            } else {
                loggedIn = false;
                loggedInUser = null;
                return null;
            }
        } catch (Exception e) {
            mess = new Message("Wrong password or Username  !!!", "3", true);
            return null;
        }
            

        
        

    }

    public String Logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        Iduser = 0;
        avtuser = null;
        loggedIn = false;
        loggedInUser = null;
        context.getExternalContext().invalidateSession();
        return "login";
    }

    public int CountAdmins() {
        return (int) usersFacade.counts("admin");
    }

    public int CountUsers() {
        return (int) usersFacade.counts("user");
    }

    public List<Users> ListAdministration() {
        return usersFacade.ListAdmin("admin");                                                                                                                                                                  
    }

    public String DeleteAdmin(Integer id) {
        Users user = usersFacade.find(id);
        if (user != null && user.getPassword().equals(password)) {                      
            usersFacade.remove(user);
            return "user-list";
        }
        return null;                    
    }

    public String findToDelete(Integer id) {
        user = usersFacade.find(id);
        return "/Admin/confirmPasswordDelete";
    }

    public String getComfirmPassword() {
        return comfirmPassword;
    }

    public void setComfirmPassword(String comfirmPassword) {
        this.comfirmPassword = comfirmPassword;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public UsersFacadeLocal getUsersFacade() {
        return usersFacade;
    }

    public void setUsersFacade(UsersFacadeLocal usersFacade) {
        this.usersFacade = usersFacade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public int getIduser() {
        return Iduser;
    }

    public void setIduser(int Iduser) {
        this.Iduser = Iduser;
    }

    public String getAvtuser() {
        return avtuser;
    }

    public void setAvtuser(String avtuser) {
        this.avtuser = avtuser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Message getMess() {
        return mess;
    }

    public void setMess(Message mess) {
        this.mess = mess;
    }

}
