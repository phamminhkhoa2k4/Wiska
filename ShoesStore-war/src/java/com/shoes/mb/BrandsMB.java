/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.shoes.mb;

import com.shoes.entity.Brands;
import com.shoes.message.Message;
import com.shoes.sb.BrandsFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ASUS
 */
@Named(value = "brandsMB")
@RequestScoped
public class BrandsMB {

    @EJB
    private BrandsFacadeLocal brandsFacade;

    private Message mess;
    private Brands brands;
    

    public BrandsMB() {
        brands = new Brands();
    }

    public String add() {
        if (brands != null && !brands.getBrandName().equals("")) {
            brandsFacade.create(brands);
            mess = new Message("Create Successfull !!!", "1", true);
            return "/Admin/brand-list";
        } else {
            mess = new Message("Please Enter Brands Name !!!", "2", true);
            return "/Admin/create-brand";
        }

    }

    public String findBrandforUpdate(int id) {
        brands = brandsFacade.find(id);
        return "/Admin/edit-brand";
    }

    public String deleteBrand(int id) { 
        
        try{
            brandsFacade.remove(brandsFacade.find(id));
            mess = new Message("Deleted Successfully  !!!", "1", true);
            return null;
        }catch(Exception ex){
                        
            mess = new Message("This Brand has been used in the product  !!!", "3", true);
            return null;
        }
        
    }

    public String edit() {
    
            if(brands.getBrandName().equals("")){
                mess = new Message("Please Enter Brands Name!!!", "2", true);
                return null;
            }else{
                try{
                    brandsFacade.edit(brands);
                    mess = new Message("Edited Successfully  !!!", "1", true);
                    return "/Admin/brand-list";
                }catch(Exception Ex){
                    mess = new Message("Erorr Server  !!!", "3", true);
                    return null;
                }
            }
      
        
    }
        
    

    
    public List<Brands> ListAll(){
        return brandsFacade.findAll();
    }
    public Brands getBrands() {
        return brands;
    }

    public void setBrands(Brands brands) {
        this.brands = brands;
    }

    public BrandsFacadeLocal getBrandsFacade() {
        return brandsFacade;
    }

    public void setBrandsFacade(BrandsFacadeLocal brandsFacade) {
        this.brandsFacade = brandsFacade;
    }

    public Message getMess() {
        return mess;
    }

    public void setMess(Message mess) {
        this.mess = mess;
    }

    

}
