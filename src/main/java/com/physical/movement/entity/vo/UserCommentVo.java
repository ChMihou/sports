package com.physical.movement.entity.vo;

import com.physical.movement.entity.common.Id;

import java.util.Date;

public class UserCommentVo extends Id {
    private static final long serialVersionUID = -2407814987574777101L;

    private String username;

    private String password;

    private String salt;

    //1为管理员 0为普通会员
    private Byte usertype;

    private String phone;

    //0为禁用用户 1为启用用户
    private Byte status;

    private String image;

    private String sex;

    private String email;

    private String truename;

    private String studentid;

    private Integer cid;

    private String comment;

    private Integer cnid;

    private Integer cuid;

    private String capply;

    private Date ctime;

    private Integer cflag;

    private String caboy;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Byte getUsertype() {
        return usertype;
    }

    public void setUsertype(Byte usertype) {
        this.usertype = usertype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

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
        this.comment = comment;
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
        this.capply = capply;
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
        this.caboy = caboy;
    }

    @Override
    public String toString() {
        return "UserCommentVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", usertype=" + usertype +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", truename='" + truename + '\'' +
                ", studentid='" + studentid + '\'' +
                ", cid=" + cid +
                ", comment='" + comment + '\'' +
                ", cnid=" + cnid +
                ", cuid=" + cuid +
                ", capply='" + capply + '\'' +
                ", ctime=" + ctime +
                ", cflag=" + cflag +
                ", caboy='" + caboy + '\'' +
                '}';
    }
}
