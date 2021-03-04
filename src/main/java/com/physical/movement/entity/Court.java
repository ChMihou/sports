package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

import java.util.Date;

/**
 * 租场实体类
 */
public class Court extends Id {
    private static final long serialVersionUID = -238160128400550657L;

    private String address;

    private Integer costtime;

    private Integer cost;

    private String type;

    private Integer tid;

    private String teamname;

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCosttime() {
        return costtime;
    }

    public void setCosttime(Integer costtime) {
        this.costtime = costtime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}