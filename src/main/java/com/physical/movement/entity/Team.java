package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

public class Team extends Id {
    private static final long serialVersionUID = -6723021836710260446L;
    private Integer id;

    private String teamname;

    private Integer teamtype;

    private Integer teamleader;

    private Integer teamleaderid;

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
}