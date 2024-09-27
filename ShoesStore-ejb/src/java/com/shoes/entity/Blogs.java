/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "Blogs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blogs.findAll", query = "SELECT b FROM Blogs b"),
    @NamedQuery(name = "Blogs.findByBlogId", query = "SELECT b FROM Blogs b WHERE b.blogId = :blogId"),
    @NamedQuery(name = "Blogs.findByTitle", query = "SELECT b FROM Blogs b WHERE b.title = :title"),
    @NamedQuery(name = "Blogs.findByTopic", query = "SELECT b FROM Blogs b WHERE b.topic = :topic"),
    @NamedQuery(name = "Blogs.findByBlogDate", query = "SELECT b FROM Blogs b WHERE b.blogDate = :blogDate"),
    @NamedQuery(name = "Blogs.findByBlogView", query = "SELECT b FROM Blogs b WHERE b.blogView = :blogView")})
public class Blogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "blog_id")
    private Integer blogId;
    @Basic(optional = false)
//    @NotNull
    @Lob
    @Size(max = 2147483647)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Basic(optional = false)
//    @NotNull
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 255)
    @Column(name = "topic")
    private String topic;
    @Basic(optional = false)
//    @NotNull
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @Column(name = "blog_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blogDate;
    @Column(name = "blog_view")
    private Integer blogView;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Blogs() {
    }

    public Blogs(Integer blogId) {
        this.blogId = blogId;
    }

    public Blogs(Integer blogId, String thumbnail, String title, String topic, String content) {
        this.blogId = blogId;
        this.thumbnail = thumbnail;
        this.title = title;
        this.topic = topic;
        this.content = content;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(Date blogDate) {
        this.blogDate = blogDate;
    }

    public Integer getBlogView() {
        return blogView;
    }

    public void setBlogView(Integer blogView) {
        this.blogView = blogView;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogId != null ? blogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blogs)) {
            return false;
        }
        Blogs other = (Blogs) object;
        if ((this.blogId == null && other.blogId != null) || (this.blogId != null && !this.blogId.equals(other.blogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shoes.entity.Blogs[ blogId=" + blogId + " ]";
    }
    
}
