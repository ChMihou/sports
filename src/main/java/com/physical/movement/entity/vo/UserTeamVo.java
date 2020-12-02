package com.physical.movement.entity.vo;

import java.io.Serializable;

public class UserTeamVo implements Serializable {

    private static final long serialVersionUID = -3606834060095467144L;
    private Integer uid;

    private String username;

    private String password;

    private String salt;

    private Byte usertype;

    private String phone;

    private Byte status;

    private String sex;

    private Integer id;

    private String teamname;

    private Integer teamtype;

    private Integer teamleader;

    private Integer teamleaderid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTeamleader() {
        return teamleader;
    }

    public void setTeamleader(Integer teamleader) {
        this.teamleader = teamleader;
    }

    public Integer getTeamleaderid() {
        return teamleaderid;
    }

    public void setTeamleaderid(Integer teamleaderid) {
        this.teamleaderid = teamleaderid;
    }
}
