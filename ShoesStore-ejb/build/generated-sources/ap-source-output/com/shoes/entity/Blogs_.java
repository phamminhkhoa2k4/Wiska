package com.shoes.entity;

import com.shoes.entity.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-07-01T15:52:02")
@StaticMetamodel(Blogs.class)
public class Blogs_ { 

    public static volatile SingularAttribute<Blogs, String> thumbnail;
    public static volatile SingularAttribute<Blogs, Integer> blogView;
    public static volatile SingularAttribute<Blogs, String> topic;
    public static volatile SingularAttribute<Blogs, Date> blogDate;
    public static volatile SingularAttribute<Blogs, String> title;
    public static volatile SingularAttribute<Blogs, Integer> blogId;
    public static volatile SingularAttribute<Blogs, Users> userId;
    public static volatile SingularAttribute<Blogs, String> content;

}