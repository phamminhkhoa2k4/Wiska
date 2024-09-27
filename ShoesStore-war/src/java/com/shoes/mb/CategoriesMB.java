/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.shoes.mb;

import com.shoes.entity.Categories;
import com.shoes.message.Message;
import com.shoes.sb.CategoriesFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ASUS
 */
@Named(value = "categoriesMB")
@RequestScoped
public class CategoriesMB {

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    private Message mess;
    private Categories categories;
    public CategoriesMB() {
        categories = new Categories();
    }
    
    public List<Categories> ListAll(){
        return categoriesFacade.findAll();
    }
    
    public String add(){
        if (categories != null && !categories.getCategoryName().equals("")) {
            categoriesFacade.create(categories);
            mess = new Message("Create Successfull !!!", "1", true);
            return "/Admin/category-list";
        } else {
            mess = new Message("Please Enter Category Name !!!", "2", true);
            return "/Admin/create-category";
        }
    }
    
    public String findCategoryforUpdate(int id){
        categories = categoriesFacade.find(id);
        return "/Admin/edit-category";
    }
    
    
    public String deleteCategory(int id){
        try{
            categoriesFacade.remove(categoriesFacade.find(id));
            mess = new Message("Deleted Successfully  !!!", "1", true);
            return null;
        }catch(Exception ex){
                        
            mess = new Message("This category has been used in the product  !!!", "3", true);
            return null;
        }
    }
    
    
     public String edit(){
         
            if(categories.getCategoryName().equals("")){
                mess = new Message("Please Enter Category Name!!!", "2", true);
                return null;
            }else{
                try{
                    categoriesFacade.edit(categories);
                    mess = new Message("Edited Successfully  !!!", "1", true);
                    return "/Admin/category-list";
                }catch(Exception Ex){
                    mess = new Message("Erorr Server  !!!", "3", true);
                    return null;
                }
            }
         
    }
     
    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Message getMess() {
        return mess;
    }

    public void setMess(Message mess) {
        this.mess = mess;
    }
    
}
