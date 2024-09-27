/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */

@Entity
@Table(name = "Products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByProductId", query = "SELECT p FROM Products p WHERE p.productId = :productId"),
    @NamedQuery(name = "Products.findByTitle", query = "SELECT p FROM Products p WHERE p.title = :title"),
    @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price"),
    @NamedQuery(name = "Products.findByThumnail", query = "SELECT p FROM Products p WHERE p.thumnail = :thumnail"),
    @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description"),
    @NamedQuery(name = "Products.findByAvailability", query = "SELECT p FROM Products p WHERE p.availability = :availability"),
    @NamedQuery(name = "Products.findBySize", query = "SELECT p FROM Products p WHERE p.size = :size"),
    @NamedQuery(name = "Products.findByWeight", query = "SELECT p FROM Products p WHERE p.weight = :weight"),
    @NamedQuery(name = "Products.findByMaterial", query = "SELECT p FROM Products p WHERE p.material = :material"),
    @NamedQuery(name = "Products.findByColor", query = "SELECT p FROM Products p WHERE p.color = :color"),
    @NamedQuery(name = "Products.findByDesigns", query = "SELECT p FROM Products p WHERE p.designs = :designs")
})
public class Products implements Serializable {

    @Size(max = 50)
    @Column(name = "category")
    private String category;

    @Column(name = "weight")
    private Integer weight;
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    @ManyToOne
    private Brands brandId;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne
    private Categories categoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<OrderDetail> orderDetailCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    @Basic(optional = false)
//    @NotNull
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
//    @NotNull
    @Lob
    @Size(max = 2147483647)
    @Column(name = "thumnail")
    private String thumnail;
    @Basic(optional = false)
//    @NotNull
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
//    @NotNull
    @Column(name = "price")
    private BigDecimal price;
//    @Size(max = 50)
//    @Column(name = "category")
//    private String category;
    @Size(max = 50)
    @Column(name = "availability")
    private String availability;
    @Size(max = 50)
    @Column(name = "size")
    private String size;
    @Size(max = 100)
    @Column(name = "material")
    private String material;
    @Size(max = 50)
    @Column(name = "color")
    private String color;
    @Size(max = 255)
    @Column(name = "designs")
    private String designs;
//    @Size(max = 100)
//    @Column(name = "type")
//    private String type;

    public Products() {
    }

    
    
    public Products(Integer productId) {
        this.productId = productId;
    }

    public Products(Integer productId, String title, String thumnail, String description, BigDecimal price) {
        this.productId = productId;
        this.title = title;
        this.thumnail = thumnail;
        this.description = description;
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesigns() {
        return designs;
    }

    public void setDesigns(String designs) {
        this.designs = designs;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shoes.entity.Products[ productId=" + productId + " ]";
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Brands getBrandId() {
        return brandId;
    }

    public void setBrandId(Brands brandId) {
        this.brandId = brandId;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
