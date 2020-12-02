package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

import java.util.Date;

public class Game extends Id {
    private static final long serialVersionUID = 4722605863454621240L;
    private Integer id;

    private Integer type;

    private String challenger;

    private Integer challengerid;

    private String enemy;

    private Integer enemyid;

    private Date gmtCreate;

    private Date gmtModified;

    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getChallenger() {
        return challenger;
    }

    public void setChallenger(String challenger) {
        this.challenger = challenger == null ? null : challenger.trim();
    }

    public Integer getChallengerid() {
        return challengerid;
    }

    public void setChallengerid(Integer challengerid) {
        this.challengerid = challengerid;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy == null ? null : enemy.trim();
    }

    public Integer getEnemyid() {
        return enemyid;
    }

    public void setEnemyid(Integer enemyid) {
        this.enemyid = enemyid;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}