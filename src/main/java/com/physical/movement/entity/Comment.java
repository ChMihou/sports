package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

import java.util.Date;

public class Comment extends Id {
    private static final long serialVersionUID = -1207988480570475255L;
    private Integer cid;

    private String comment;

    private Integer cnid;

    private Integer cuid;

    private String capply;

    private Date ctime;

    private Integer cflag;

    private String caboy;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getCnid() {
        return cnid;
    }

    public void setCnid(Integer cnid) {
        this.cnid = cnid;
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public String getCapply() {
        return capply;
    }

    public void setCapply(String capply) {
        this.capply = capply == null ? null : capply.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getCflag() {
        return cflag;
    }

    public void setCflag(Integer cflag) {
        this.cflag = cflag;
    }

    public String getCaboy() {
        return caboy;
    }

    public void setCaboy(String caboy) {
        this.caboy = caboy == null ? null : caboy.trim();
    }
}