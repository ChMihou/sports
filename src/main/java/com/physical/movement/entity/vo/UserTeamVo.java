package com.physical.movement.entity.vo;

import com.physical.movement.entity.common.Id;

import java.io.Serializable;
import java.util.Date;

public class UserTeamVo extends Id implements Serializable {

    private static final long serialVersionUID = -3606834060095467144L;
    private String username;

    private String password;

    private String salt;

    private Byte usertype;

    private String phone;

    private Byte status;

    private String image;

    private String sex;

    private String email;

    private String truename;

    private String studentid;

    private String teamname;

    private Integer teamtype;

    private String teamleader;

    private Integer teamleaderid;

    private Byte flag;

    private String reason;

    private String cause;

    private String intro;

    private String teamemail;

    private Date gmtCreate;

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

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public Integer getTeamtype() {
        return teamtype;
    }

    public void setTeamtype(Integer teamtype) {
        this.teamtype = teamtype;
    }

    public String getTeamleader() {
        return teamleader;
    }

    public void setTeamleader(String teamleader) {
        this.teamleader = teamleader;
    }

    public Integer getTeamleaderid() {
        return teamleaderid;
    }

    public void setTeamleaderid(Integer teamleaderid) {
        this.teamleaderid = teamleaderid;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTeamemail() {
        return teamemail;
    }

    public void setTeamemail(String teamemail) {
        this.teamemail = teamemail;
    }
}
