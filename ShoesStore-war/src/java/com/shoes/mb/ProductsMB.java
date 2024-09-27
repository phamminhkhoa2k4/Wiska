package com.shoes.mb;

import com.shoes.entity.Products;
import com.shoes.message.Message;
import com.shoes.sb.BrandsFacadeLocal;
import com.shoes.sb.CategoriesFacadeLocal;
import com.shoes.sb.ProductsFacadeLocal;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author ASUS
 */
@ManagedBean
@Named(value = "productsMB")
@RequestScoped
public class ProductsMB implements Serializable {

    @EJB
    private BrandsFacadeLocal brandsFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private ProductsFacadeLocal productsFacade;

    private Products product;
    private Part file;

    private int pageNumber = 1;
    private int pageSize = 4;
    private long totalProducts;
    private List<Products> products;
    private Message mess;

    private int categoryId;
    private int brandId;

    public ProductsMB() {
        product = new Products();
    }

    public String addProduct() throws FileNotFoundException, IOException, Exception {
        if (product != null) {
            if (product.getTitle().equals("")) {
                mess = new Message("Please Enter Title !!!", "2", true);
                return null;
            } else if (product.getPrice() == null) {
                mess = new Message("Please Enter Price !!!", "2", true);
                return null;
            } else if (file == null) {
                mess = new Message("Please Choose Thumbnail!!!", "2", true);
                return null;
            } else if (product.getAvailability().equals("")) {
                mess = new Message("Please Enter Availability !!!", "2", true);
                return null;
            } else if (product.getMaterial().equals("")) {
                mess = new Message("Please Enter Material !!!", "2", true);
                return null;
            } else if (product.getSize().equals("")) {
                mess = new Message("Please Enter Size !!!", "2", true);
                return null;
            } else if (product.getColor().equals("")) {
                mess = new Message("Please Enter Color !!!", "2", true);
                return null;
            } else if (product.getWeight() == null) {
                mess = new Message("Please Enter Weight!!!", "2", true);
                return null;
            } else if (product.getDesigns().equals("")) {
                mess = new Message("Please Enter Designs!!!", "2", true);
                return null;
            } else if (product.getDescription().equals("")) {
                mess = new Message("Please Enter Description !!!", "2", true);
                return null;
            } else {

                String filename = file.getSubmittedFileName();
                product.setThumnail(file.getSubmittedFileName());
                InputStream input = file.getInputStream();
                FileOutputStream output = new FileOutputStream("F:\\ShoesStore\\ShoesStore-war\\web\\resources\\products\\" + filename);
                byte[] buf = new byte[1024];
                int len;
                while ((len = input.read(buf)) > 0) {
                    output.write(buf, 0, len);
                }
                try {
                    product.setBrandId(brandsFacade.find(brandId));
                    product.setCategoryId(categoriesFacade.find(categoryId));
                    productsFacade.create(product);
                    mess = new Message("Created Successfully !!!", "1", true);
                    return "/Admin/product-list";
                } catch (Exception ex) {
                    mess = new Message("Error Server  !!!", "3", true);
                    return null;
                }

            }
        } else {
            mess = new Message("Please fill in fields !!!", "2", true);
            return null;
        }

    }

    public String editProduct() throws FileNotFoundException, IOException {
        if (product != null) {
            if (product.getTitle().equals("")) {
                mess = new Message("Please Enter Title !!!", "2", true);
                return null;
            } else if (product.getPrice() == null) {
                mess = new Message("Please Enter Price !!!", "2", true);
                return null;
            }  else if (product.getAvailability().equals("")) {
                mess = new Message("Please Enter Availability !!!", "2", true);
                return null;
            } else if (product.getMaterial().equals("")) {
                mess = new Message("Please Enter Material !!!", "2", true);
                return null;
            } else if (product.getSize().equals("")) {
                mess = new Message("Please Enter Size !!!", "2", true);
                return null;
            } else if (product.getColor().equals("")) {
                mess = new Message("Please Enter Color !!!", "2", true);
                return null;
            } else if (product.getWeight() == null) {
                mess = new Message("Please Enter Weight!!!", "2", true);
                return null;
            } else if (product.getDesigns().equals("")) {
                mess = new Message("Please Enter Designs!!!", "2", true);
                return null;
            } else if (product.getDescription().equals("")) {
                mess = new Message("Please Enter Description !!!", "2", true);
                return null;
            } else {
                if (file != null) {
                    String filename = file.getSubmittedFileName();
                    product.setThumnail(file.getSubmittedFileName());
                    InputStream input = file.getInputStream();
                    FileOutputStream output = new FileOutputStream("F:\\ShoesStore\\ShoesStore-war\\web\\resources\\products\\" + filename);
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = input.read(buf)) > 0) {
                        output.write(buf, 0, len);
                    }
                } else {
                    product.setThumnail(productsFacade.find(product.getProductId()).getThumnail());
                }
                try {
                    product.setBrandId(brandsFacade.find(brandId));
                    product.setCategoryId(categoriesFacade.find(categoryId));
                    productsFacade.edit(product);
                    mess = new Message("Edited Successfully !!!", "1", true);
                    return "/Admin/product-list";
                } catch (Exception e) {
                    mess = new Message("Error Server  !!!", "3", true);
                    return null;
                }
            }
        } else {
            mess = new Message("Please fill in fields !!!", "2", true);
            return null;
        }

    }

    public String findProductforUpdate(int id) {
        product = productsFacade.find(id);
        return "/Admin/edit-product";
    }

    public String findProductDetail(int id) {
        product = productsFacade.find(id);
        return "productDetail";
    }

    public String addToCart(int quantity, int userId) {
        // Retrieve productId from request parameter
        String productIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productId");

        if (productIdParam != null && quantity != 0) {
            // Convert productIdParam to Integer
            Integer productId = Integer.valueOf(productIdParam);

            // Fetch product data based on productId
            product = productsFacade.find(productId);

            if (product != null && userId != 0) {
                // Get the session-scoped CartMB bean
                CartMB cartBean = FacesContext.getCurrentInstance().getApplication()
                        .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{cartMB}", CartMB.class);

                // Add the selected product to the cart
                cartBean.addItem(product.getProductId(), quantity, userId);

                // Redirect to cart page or wherever you want
                return "cart.xhtml?faces-redirect=true";
            } else {
                return "login";
            }
        }

        return null;
    }

    public int productCount() {
        return (int) productsFacade.counts();
    }

    public int maxPage() {
        int maxPageSize = (int) (totalProducts / pageSize) + 1;
        return maxPageSize;
    }

    public String deleteProduct(int id) {
        try{
            productsFacade.remove(productsFacade.find(id));
             mess = new Message("Deleted Successfully !!!", "1", true);
             return null;
        }catch(Exception ex){
            mess = new Message("Error Server  !!!", "3", true);
            return null;
        }
        
        
    }

    public List<Products> showAllProduct() {
        try {
            List<Products> data = productsFacade.findAll();
            return data;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Products> getProducts(int pageNumber, int pageSize) {
        return productsFacade.findAll(pageNumber, pageSize);
    }

    public long getTotalProduct() {
        return productsFacade.counts();
    }

    public void loadProducts() {
        products = this.getProducts(pageNumber, pageSize);
        totalProducts = this.getTotalProduct();
    }

    public void nextPage() {
        this.pageNumber = pageNumber + 1;
        loadProducts();
    }

    public void previousPage() {
        if (pageNumber > 1) {
            pageNumber--;
        }
        loadProducts();
    }

    public List<Products> getProducts() {
        if (products == null) {
            loadProducts();
        }
        return products;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Products> ListLatest() {
        return productsFacade.findRange(new int[]{0, 10});
    }

    public List<Products> ListBanner() {
        return productsFacade.findRange(new int[]{0, 3});
    }

    public List<Products> ListComming() {
        List<Products> data = productsFacade.getProductsByComming("in stock");
        return data;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public Message getMess() {
        return mess;
    }

    public void setMess(Message mess) {
        this.mess = mess;
    }

}

//if(product != null){
//            if(product.getTitle().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getTitle().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getPrice().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getCategoryId() == null){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getThumnail().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getAvailability().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getMaterial().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getBrandId() == null ){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getSize().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getColor().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getWeight().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getDesigns().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else if(product.getDescription().equals("")){
//                mess = new Message("Please Enter !!!", "2", true);
//            }else{
//                mess = new Message("Create Successfully !!!", "1", true);
//                mess = new Message("Error Server  !!!", "3", true);
//            }
//        }else{
//            mess = new Message("Please fill in fields !!!", "2", true);
//        }
