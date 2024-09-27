package com.shoes.entity;

import com.shoes.entity.Brands;
import com.shoes.entity.Categories;
import com.shoes.entity.OrderDetail;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-07-01T15:52:02")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile CollectionAttribute<Products, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Products, Integer> productId;
    public static volatile SingularAttribute<Products, String> color;
    public static volatile SingularAttribute<Products, String> thumnail;
    public static volatile SingularAttribute<Products, Integer> weight;
    public static volatile SingularAttribute<Products, String> description;
    public static volatile SingularAttribute<Products, String> availability;
    public static volatile SingularAttribute<Products, String> title;
    public static volatile SingularAttribute<Products, String> size;
    public static volatile SingularAttribute<Products, String> material;
    public static volatile SingularAttribute<Products, BigDecimal> price;
    public static volatile SingularAttribute<Products, Brands> brandId;
    public static volatile SingularAttribute<Products, String> designs;
    public static volatile SingularAttribute<Products, String> category;
    public static volatile SingularAttribute<Products, Categories> categoryId;

}