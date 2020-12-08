package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

public class Team extends Id {

    private String teamname;

    private Integer teamtype;

    private Integer teamleader;

    private Integer teamleaderid;

    private Byte flag;

    private String reason;

    private String cause;


    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname == null ? null : teamname.trim();
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

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }
}