package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

import java.util.Date;

public class Advisory extends Id {
    private static final long serialVersionUID = -1506031664527103014L;
    private Integer id;

    private String title;

    private String checkboy;

    private Integer flag;

    private String author;

    private Integer nselect;

    private String article;

    private String ncause;

    private String intro;

    private String nimage;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCheckboy() {
        return checkboy;
    }

    public void setCheckboy(String checkboy) {
        this.checkboy = checkboy == null ? null : checkboy.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNcause() {
        return ncause;
    }

    public void setNcause(String ncause) {
        this.ncause = ncause;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getNselect() {
        return nselect;
    }

    public void setNselect(Integer nselect) {
        this.nselect = nselect;
    }

    public String getNimage() {
        return nimage;
    }

    public void setNimage(String nimage) {
        this.nimage = nimage == null ? null : nimage.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}