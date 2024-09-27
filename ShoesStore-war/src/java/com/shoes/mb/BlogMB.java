/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.shoes.mb;

import com.shoes.entity.Blogs;
import com.shoes.message.Message;
import com.shoes.sb.BlogsFacadeLocal;
import com.shoes.sb.UsersFacadeLocal;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

/**
 *
 * @author ASUS
 */
@Named(value = "blogMB")
@RequestScoped
public class BlogMB {

    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private BlogsFacadeLocal blogsFacade;

    private Message mess;
    private Blogs blog;
    private Part file;

    public BlogMB() {
        blog = new Blogs();
    }

    public String add(int userId) throws FileNotFoundException, IOException {
        if (blog != null) {
            if (file == null) {
                mess = new Message("Please Enter Thumbnail!!!", "2", true);
                return null;
            } else if (blog.getTitle().equals("")) {
                mess = new Message("Please Enter Title !!!", "2", true);
                return null;
            } else if (blog.getTopic().equals("")) {
                mess = new Message("Please Enter  Topic!!!", "2", true);
                return null;
            } else if (blog.getContent().equals("")) {
                mess = new Message("Please Enter Content!!!", "2", true);
                return null;
            } else {
                String filename = file.getSubmittedFileName();
                blog.setThumbnail(file.getSubmittedFileName());
                InputStream input = file.getInputStream();
                FileOutputStream output = new FileOutputStream("F:\\ShoesStore\\ShoesStore-war\\web\\resources\\blogs\\" + filename);
                byte[] buf = new byte[1024];
                int len;
                while ((len = input.read(buf)) > 0) {
                    output.write(buf, 0, len);
                }
                blog.setBlogDate(new Date());
                blog.setBlogView(1);
                try {
                    blog.setUserId(usersFacade.find(userId));
                    blogsFacade.create(blog);
                    mess = new Message("Create Successfully !!!", "1", true);
                    return "/Admin/blog-list";
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

    public String detailAndIncrease(int id) {
        Blogs blogs = blogsFacade.find(id);
        int view = blogs.getBlogView();
        blogs.setBlogView(++view);
        blogs.setBlogDate(blogsFacade.find(id).getBlogDate());
        blogsFacade.edit(blogs);
        blog = blogsFacade.find(id);
        return "blogDetail";
    }

    public String findBlogforUpdate(int id) {
        blog = blogsFacade.find(id);
        return "/Admin/edit-blog";
    }

    public String deleteBlog(int id) {
        try {
            blogsFacade.remove(blogsFacade.find(id));
            mess = new Message("Deleted Successfully !!!", "1", true);
            return null;
        } catch (Exception e) {
            mess = new Message("Error Server  !!!", "3", true);
            return null;
        }

    }

    public String edit(int userId) throws FileNotFoundException, IOException {
        if (blog != null) {
             if (blog.getTitle().equals("")) {
                mess = new Message("Please Enter Title!!!", "2", true);
                return null;
            } else if (blog.getTopic().equals("")) {
                mess = new Message("Please Enter Topic!!!", "2", true);
                return null;
            } else if (blog.getContent().equals("")) {
                mess = new Message("Please Enter Content!!!", "2", true);
                return null;
            } else {

                try {
                    if (file != null) {
                        String filename = file.getSubmittedFileName();
                        blog.setThumbnail(file.getSubmittedFileName());
                        InputStream input = file.getInputStream();
                        FileOutputStream output = new FileOutputStream("F:\\ShoesStore\\ShoesStore-war\\web\\resources\\blogs\\" + filename);
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = input.read(buf)) > 0) {
                            output.write(buf, 0, len);
                        }
                    } else {
                        blog.setThumbnail(blogsFacade.find(blog.getBlogId()).getThumbnail());
                    }
                    blog.setBlogDate(blogsFacade.find(blog.getBlogId()).getBlogDate());
                    blog.setUserId(usersFacade.find(userId));
                    blogsFacade.edit(blog);
                    mess = new Message("Edited Successfully !!!", "1", true);
                    return "/Admin/blog-list";
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

    public List<Blogs> ListAll() {
        return blogsFacade.findAll();
    }

    public Blogs getBlog() {
        return blog;
    }

    public void setBlog(Blogs blog) {
        this.blog = blog;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Message getMess() {
        return mess;
    }

    public void setMess(Message mess) {
        this.mess = mess;
    }

}
