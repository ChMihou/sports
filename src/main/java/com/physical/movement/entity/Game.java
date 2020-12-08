package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

public class Game extends Id {

    private Integer type;

    private String challenger;

    private Integer challengerid;

    private String enemy;

    private Integer enemyid;

    private Byte flag;

    private String result;

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

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}