/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.shoes.mb;

import com.shoes.entity.Blogs;
import com.shoes.entity.Brands;
import com.shoes.entity.Categories;
import com.shoes.entity.Contacts;
import com.shoes.entity.Orders;
import com.shoes.entity.Products;
import com.shoes.entity.Users;
import com.shoes.sb.BlogsFacadeLocal;
import com.shoes.sb.BrandsFacadeLocal;
import com.shoes.sb.CategoriesFacadeLocal;
import com.shoes.sb.ContactsFacadeLocal;
import com.shoes.sb.OrdersFacadeLocal;
import com.shoes.sb.ProductsFacadeLocal;
import com.shoes.sb.UsersFacadeLocal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author ASUS
 */
@Named(value = "searchMB")
@ViewScoped
public class SearchMB implements Serializable {

    @EJB
    private ContactsFacadeLocal contactsFacade;

    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    
    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private BlogsFacadeLocal blogsFacade;

    @EJB
    private BrandsFacadeLocal brandsFacade;

    @EJB
    private ProductsFacadeLocal productsFacade;

    
    private String searchName;
    
    private String searchTitle;
    private String searchTitleBlog;
    private String searchNameOrder;
    private String searchNameCategory;
    private String searchMessContact;
    private String searchUsernameAdmin;




    private List<Products> searchResults;
    public SearchMB() {
       
    }
    
    public void search() {
        if (searchTitle != null && !searchTitle.isEmpty()) {
            searchResults = productsFacade.searchProductsByTitle(searchTitle);       
        } else {
            searchResults = null;
        }
    }
    
    
    public List<Brands> searchListAllBrand() {
        if (searchName != null && !searchName.isEmpty()) {
            List<Brands> brandsList = brandsFacade.searchBrandsByName(searchName);
            Collections.reverse(brandsList);
            return brandsList;
        } else {
            List<Brands> brandsList = brandsFacade.findAll();
            Collections.reverse(brandsList);
            return brandsList;
        }

    }
    
    public List<Blogs> searchListAllBlog() {
        if (searchTitleBlog != null && !searchTitleBlog.isEmpty()) {
            List<Blogs> blogsList = blogsFacade.searchBlogsByTitle(searchTitleBlog);
            Collections.reverse(blogsList);
            return blogsList;
        } else {
            List<Blogs> blogsList = blogsFacade.findAll();
            Collections.reverse(blogsList);
            return blogsList;
        }

    }
    
    public List<Categories> searchListAllCategory() {
        if (searchNameCategory != null && !searchNameCategory.isEmpty()) {
            List<Categories> CateList = categoriesFacade.searchCategoriesByName(searchNameCategory);
            Collections.reverse(CateList);
            return CateList;
        } else {
            List<Categories> CateList = categoriesFacade.findAll();
            Collections.reverse(CateList);
            return CateList;
        }

    }
    
    public List<Contacts> searchListAllContact() {
        if (searchMessContact != null && !searchMessContact.isEmpty()) {
            List<Contacts> contactList = contactsFacade.searchContactsByMess(searchMessContact);
            Collections.reverse(contactList);
            return contactList;
        } else {
            List<Contacts> contactList = contactsFacade.findAll();
            Collections.reverse(contactList);
            return contactList;
        }

    }
    
    public List<Orders> searchListAllOrder() {
        if (searchNameOrder != null && !searchNameOrder.isEmpty()) {
            List<Orders> OrderList = ordersFacade.searchOrdersByName(searchNameOrder);
            Collections.reverse(OrderList);
            return OrderList;
        } else {
            List<Orders> OrderList = ordersFacade.findAll();
            Collections.reverse(OrderList);
            return OrderList;
        }

    }
    
    public List<Products> searchListAllProduct() {
        if (searchTitle != null && !searchTitle.isEmpty()) {
            List<Products> ProductList = productsFacade.searchProductsByTitle(searchTitle);
            Collections.reverse(ProductList);
            return ProductList;
        } else {
            List<Products> ProductList = productsFacade.findAll();
            Collections.reverse(ProductList);
            return ProductList;
        }

    }
    
    public List<Users> searchListAllAdmin() {
        if (searchUsernameAdmin != null && !searchUsernameAdmin.isEmpty()) {
            List<Users> AdminList = usersFacade.searchAdminByUsername(searchUsernameAdmin);
            Collections.reverse(AdminList);
            return AdminList;
        } else {
            List<Users> AdminList = usersFacade.ListAdmin("admin");
            Collections.reverse(AdminList);
            return AdminList;
        }

    }
    
    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public List<Products> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Products> searchResults) {
        this.searchResults = searchResults;
    }
    
    
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchTitleBlog() {
        return searchTitleBlog;
    }

    public void setSearchTitleBlog(String searchTitleBlog) {
        this.searchTitleBlog = searchTitleBlog;
    }

    public String getSearchNameCategory() {
        return searchNameCategory;
    }

    public void setSearchNameCategory(String searchNameCategory) {
        this.searchNameCategory = searchNameCategory;
    }

    public String getSearchMessContact() {
        return searchMessContact;
    }

    public void setSearchMessContact(String searchMessContact) {
        this.searchMessContact = searchMessContact;
    }

    public String getSearchNameOrder() {
        return searchNameOrder;
    }

    public void setSearchNameOrder(String searchNameOrder) {
        this.searchNameOrder = searchNameOrder;
    }

    public String getSearchUsernameAdmin() {
        return searchUsernameAdmin;
    }

    public void setSearchUsernameAdmin(String searchUsernameAdmin) {
        this.searchUsernameAdmin = searchUsernameAdmin;
    }
    
}
