/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shoes.sb;

import com.shoes.entity.Blogs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS
 */
@Local
public interface BlogsFacadeLocal {

    void create(Blogs blogs);

    void edit(Blogs blogs);

    void remove(Blogs blogs);

    Blogs find(Object id);

    List<Blogs> findAll();

    List<Blogs> findRange(int[] range);

    public List<Blogs> searchBlogsByTitle(String BlogTitle);
    int count();
    
}
